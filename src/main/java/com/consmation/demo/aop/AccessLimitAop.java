package com.consmation.demo.aop;

import com.consmation.demo.anno.AccessLimit;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:15:23
 */
@Component
@Aspect
public class AccessLimitAop {

    /**
     * 根据请求地址保存不同的令牌桶
     */
    private static final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<String,RateLimiter>();

    @Pointcut("@annotation(com.consmation.demo.anno.AccessLimit)")
    public void pointCut(){}

    @Around("pointCut() && @annotation(accessLimit)")   //accessLimit 要 和传参保持一致
    public Object around(ProceedingJoinPoint pjp ,AccessLimit accessLimit){
        //获取 request
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取url
        String url = request.getRequestURI();
        // 定义令牌桶
        RateLimiter rateLimiter;

        if(!rateLimiterMap. containsKey(url)){
            // 为当前请求创建令牌桶
            rateLimiter = RateLimiter.create(accessLimit.QPS());
            rateLimiterMap.put(url,rateLimiter);
        }

        // 根据请求 url 获取令牌桶
        rateLimiter = rateLimiterMap.get(url);
        // 获取令牌
        boolean acquire = rateLimiter.tryAcquire(accessLimit.acquireTokenTimeout(), accessLimit.timeunit());
        if(!acquire){
            throw new CustomException(AppHttpCodeEnum.SERVER_BUSY);
        }
        // 调用目标方法
        Object proceed =null;
        try {
            proceed = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }

}
