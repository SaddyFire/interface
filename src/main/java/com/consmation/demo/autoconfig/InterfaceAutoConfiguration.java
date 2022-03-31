package com.consmation.demo.autoconfig;

import com.consmation.demo.autoconfig.properties.InterfaceProperties;
import com.consmation.demo.autoconfig.properties.OssProperties;
import com.consmation.demo.autoconfig.template.OssTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:13:39
 */
@EnableConfigurationProperties({
        OssProperties.class,
        InterfaceProperties.class
})
public class InterfaceAutoConfiguration {

    @Bean
    public OssTemplate ossTemplate(OssProperties properties) {
        return new OssTemplate(properties);
    }

}
