package com.consmation.demo.config;

import com.consmation.demo.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet .config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类  
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public HandlerInterceptor getAuthorizationInterceptor(){
//        return new AuthorizationInterceptor();
//    }

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/user/loginVerification"
                );

        //此处可继续添加

    }
}