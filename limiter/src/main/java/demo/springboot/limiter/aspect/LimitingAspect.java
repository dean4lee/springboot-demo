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
 * @author dean.lee
 *
 * aop操作日志记录
 */
@Aspect
@Component
public class LimitingAspect {
    //redis中存储的key
    private static final String LIMITER_KEY = "limiter:%s:%s";
    private static final String LIMITER_BEGINTIME = "beginTime";
    private static final String LIMITER_EXFREQUENCY = "exFrequency";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(limiter)")
    public void pointcut(Limiter limiter) {}

    @Around("pointcut(limiter)")
    public Object around(ProceedingJoinPoint pjp, Limiter limiter) throws Throwable {
        //获取请求的ip和访问方法的名称
        String ipAddress = WebUtil.getIpAddress();
        String methodName = pjp.getSignature().toLongString();

        //获取方法的访问周期和频率
        long cycle = limiter.cycle();
        int frequency = limiter.frequency();
        //获取访问方法的时间
        long currentTime = System.currentTimeMillis();

        //获取redis中周期内第一次访问方法的时间和执行的次数
        Long beginTime = (Long) redisTemplate.opsForHash().get(String.format(LIMITER_KEY, ipAddress, methodName), LIMITER_BEGINTIME);
        Integer exFrequency = (Integer) redisTemplate.opsForHash().get(String.format(LIMITER_KEY, ipAddress, methodName), LIMITER_EXFREQUENCY);

        beginTime = beginTime == null ? 0 : beginTime;
        exFrequency = exFrequency == null ? 0 : exFrequency;

        //如果当前时间减去周期内第一次访问方法的时间大于周期时间，则正常访问
        //并将周期内第一次访问方法的时间和执行次数初始化
        if(currentTime - beginTime > cycle){
            redisTemplate.opsForHash().put(String.format(LIMITER_KEY, ipAddress, methodName), LIMITER_BEGINTIME, currentTime);
            redisTemplate.opsForHash().put(String.format(LIMITER_KEY, ipAddress, methodName), LIMITER_EXFREQUENCY, 1);
            //设置过期时间
            redisTemplate.expire(String.format(LIMITER_KEY, ipAddress, methodName), limiter.cycle(), TimeUnit.MILLISECONDS);
            return pjp.proceed();
        }else{
            //如果在周期时间内，执行次数小于频率，则正常访问
            //并将执行次数加一
            if(exFrequency < frequency){
                redisTemplate.opsForHash().put(String.format(LIMITER_KEY, ipAddress, methodName), LIMITER_EXFREQUENCY, exFrequency + 1);
                redisTemplate.expire(String.format(LIMITER_KEY, ipAddress, methodName), limiter.cycle(), TimeUnit.MILLISECONDS);
                return pjp.proceed();
            }else {
                //否则抛出访问频繁异常
                throw new FrequentRequestsException(limiter.message());
            }
        }
    }
}
