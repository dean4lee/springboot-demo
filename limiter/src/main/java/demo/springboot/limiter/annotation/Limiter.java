package demo.springboot.limiter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dean.lee
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limiter {

    /**
     * 从第一次访问接口的时间到cycle周期时间内，无法超过frequency次
     *
     * @return
     */
    int frequency() default 20;

    /**
     * 周期时间,单位ms：
     * 默认周期时间为一分钟
     *
     * @return
     */
    long cycle() default 60 * 1000;

    /**
     * 返回的错误信息
     *
     * @return
     */
    String message() default "请求过于频繁";

    /**
     * 到期时间,单位s：
     * 如果在cycle周期时间内超过frequency次，则默认1分钟内无法继续访问
     * @return
     */
    long expireTime() default 1 * 60;
}
