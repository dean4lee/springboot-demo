package demo.springboot.limiter.aspect;

import demo.springboot.limiter.annotation.Limiter;
import demo.springboot.limiter.exception.FrequentRequestsException;
import demo.springboot.limiter.util.WebUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * ip防刷api功能实现。
 * 该功能使用redis作为存储，方便在集群中使用。
 * 如果是单项目部署，可以将redis换成本地缓存。
 *
 * @author dean.lee
 * <p>
 */
@Aspect
@Component
public class LimitingAspect {
    private static final String LIMITING_KEY = "limiting:%s:%s";
    private static final String LIMITING_BEGINTIME = "beginTime";
    private static final String LIMITING_EXFREQUENCY = "exFrequency";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(limiter)")
    public void pointcut(Limiter limiter) {
    }

    @Around("pointcut(limiter)")
    public Object around(ProceedingJoinPoint pjp, Limiter limiter) throws Throwable {
        //获取请求的ip和方法
        String ipAddress = WebUtil.getIpAddress();
        String methodName = pjp.getSignature().toLongString();

        //获取方法的访问周期和频率
        long cycle = limiter.cycle();
        int frequency = limiter.frequency();
        long currentTime = System.currentTimeMillis();

        //获取redis中周期内第一次访问方法的时间和执行的次数
        Long beginTimeLong = (Long) redisTemplate.opsForHash().get(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_BEGINTIME);
        Integer exFrequencyLong = (Integer) redisTemplate.opsForHash().get(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_EXFREQUENCY);

        long beginTime = beginTimeLong == null ? 0L : beginTimeLong;
        int exFrequency = exFrequencyLong == null ? 0 : exFrequencyLong;

        //如果当前时间减去周期内第一次访问方法的时间大于周期时间，则正常访问
        //并将周期内第一次访问方法的时间和执行次数初始化
        if (currentTime - beginTime > cycle) {
            redisTemplate.opsForHash().put(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_BEGINTIME, currentTime);
            redisTemplate.opsForHash().put(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_EXFREQUENCY, 1);
            redisTemplate.expire(String.format(LIMITING_KEY, ipAddress, methodName), limiter.expireTime(), TimeUnit.SECONDS);
            return pjp.proceed();
        } else {
            //如果在周期时间内，执行次数小于频率，则正常访问
            //并将执行次数加一
            if (exFrequency < frequency) {
                redisTemplate.opsForHash().put(String.format(LIMITING_KEY, ipAddress, methodName), LIMITING_EXFREQUENCY, exFrequency + 1);
                redisTemplate.expire(String.format(LIMITING_KEY, ipAddress, methodName), limiter.expireTime(), TimeUnit.SECONDS);
                return pjp.proceed();
            } else {
                //否则抛出访问频繁异常
                throw new FrequentRequestsException(limiter.message());
            }
        }
    }
}