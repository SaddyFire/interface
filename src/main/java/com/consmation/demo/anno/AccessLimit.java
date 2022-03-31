package com.consmation.demo.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:15:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * 这里指吞吐率每秒多少许可数（通常是指QPS，每秒多少查询）
     * @return
     */
    double QPS() default 10D;

    /**
     * 获取令牌超时时间
     * @return
     */
    long acquireTokenTimeout() default 100;

    /**
     * 获取令牌超时时间单位：默认为 毫秒
     * @return
     */
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

}
