package demo.springboot.aop.aspect;

import com.alibaba.fastjson.JSONObject;
import demo.springboot.aop.annotation.Print;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author dean.lee
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* demo.springboot.aop.ctrl.*.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void printParam(JoinPoint joinPoint){
        //获取请求的方法
        Signature sig = joinPoint.getSignature();
        String method = joinPoint.getTarget().getClass().getName() + "." + sig.getName();

        //获取请求的参数
        Object[] args = joinPoint.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        //打印请求参数
        log.info(method + ":" + params);
    }

    @Pointcut("@annotation(print)")
    public void annotationPointcut(Print print){}

    @Around("annotationPointcut(print)")
    public Object around(ProceedingJoinPoint pjp, Print print) throws Throwable {
        //获取请求方法
        Signature sig = pjp.getSignature();
        String method = pjp.getTarget().getClass().getName() + "." + sig.getName();

        //获取请求的参数
        Object[] args = pjp.getArgs();
        //fastjson转换
        String params = JSONObject.toJSONString(args);

        //打印请求参数
        log.info("参数:" + method + ":" + params);

        //执行方法
        Object result = pjp.proceed();

        //打印返回结果
        log.info("返回结果:" + method + ":" + result);
        return result;
    }
}
