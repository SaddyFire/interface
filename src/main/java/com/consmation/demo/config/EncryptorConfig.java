package com.consmation.demo.config;

import com.consmation.demo.handler.StringEncryptorHandler;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author SaddyFire
 * @date 2022/3/29
 * @TIME:11:57
 * 处理器配置类
 */
@Configuration
public class EncryptorConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        StringEncryptorHandler desEncrypt = new StringEncryptorHandler();//调用我们自己实现的类即可
        return desEncrypt;
    }
}
