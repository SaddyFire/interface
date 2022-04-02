package com.consmation.demo.handler;

import com.consmation.demo.utils.Constants;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

/**
 * @author SaddyFire
 * @date 2022/3/29
 * @TIME:13:37
 * Encryptor处理器
 * 针对数据库的password密文处理
 */

public class StringEncryptorHandler implements StringEncryptor {

    //自定义加密
    @Override
    public String encrypt(String message) {
        try {
            StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
            EnvironmentPBEConfig config = new EnvironmentPBEConfig();
            // 默认加密算法
            config.setAlgorithm("PBEWithMD5AndDES");
            // 设置加密密钥
            config.setPassword(Constants.encryptorPassword);
            standardPBEStringEncryptor.setConfig(config);
            String encrypt = standardPBEStringEncryptor.encrypt(message);
            //返回加密后message
            return encrypt;
        } catch (Exception e) {
            e.printStackTrace();
            return message;
        }
    }
    //自定义解密
    @Override
    public String decrypt(String encryptedMessage) {
        try {
            StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
            EnvironmentPBEConfig config = new EnvironmentPBEConfig();
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setPassword(Constants.encryptorPassword);
            standardPBEStringEncryptor.setConfig(config);
            String decrypt = standardPBEStringEncryptor.decrypt(encryptedMessage);
            //返回解密后message
            return decrypt;
        } catch (Exception e) {
            e.printStackTrace();
            return encryptedMessage;
        }
    }
}
