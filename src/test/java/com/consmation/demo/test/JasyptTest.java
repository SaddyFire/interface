package com.consmation.demo.test;

import com.consmation.demo.InterfaceApplication;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SaddyFire
 * @date 2022/3/29
 * @TIME:11:29
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InterfaceApplication.class)
public class JasyptTest {

    @Test
    public void testEncrypt() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");          // 加密的算法，这个算法是默认的
        config.setPassword("interface");                        // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "2323";  //w8IoQjdyZt5sfw/LHEresw==
        String plainText2 = "interface123";     //interface123 ->  xmtij2TpTa3FQpvvFR6xkJyyJgTfWzUd
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText2);


        System.out.println(encryptedText);
    }

    @Test
    public void testDe() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("interface");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "FTvZOgbWC6d7PFrIfoVqzg==";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

}
