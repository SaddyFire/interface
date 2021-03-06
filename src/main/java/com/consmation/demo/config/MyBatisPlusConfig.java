package com.consmation.demo.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:26
 * 处理器配置类
 */

@Configuration
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor getMPI(){
        //创建拦截器容器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //向容器增加分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }
}
