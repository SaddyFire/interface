package com.consmation.demo.test;

import com.consmation.demo.InterfaceApplication;
import com.consmation.demo.model.vo.UserVo;
import com.consmation.demo.utils.Constants;
import com.consmation.demo.utils.RSAUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.security.KeyPair;

/**
 * @author SaddyFire
 * @date 2022/3/25
 * @TIME:9:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InterfaceApplication.class)
public class RSADTest02 {

    /**
     * 加解密测试
     */
    @Test
    public void test01() throws Exception {
        //生成秘钥对
        KeyPair keyPair = RSAUtil.generateRSAKeyPair(1023);
        String publicKeyStr = RSAUtil.getPublicKeyStr(keyPair);

        //封装对象
        UserVo user01 = UserVo.builder()
                .id(1L)
                .name("张三")
                .age(23).build();
        String userJson = "{\"age\":23,\"id\":1,\"name\":\"张三\"},{\"age\":24,\"id\":2,\"name\":\"李四\"},{\"age\":25,\"id\":3,\"name\":\"王五\"},{\"age\":26,\"id\":4,\"name\":\"赵六\"},{\"age\":27,\"id\":5,\"name\":\"钱七\"},{\"age\":28,\"id\":6,\"name\":\"勾八\"},{\"age\":29,\"id\":7,\"name\":\"西西\"},{\"age\":30,\"id\":8,\"name\":\"东东\"},{\"age\":31,\"id\":9,\"name\":\"楠楠\"},{\"age\":32,\"id\":10,\"name\":\"北北\"}";
        //公钥分段加密
        String publicEncrpyt = RSAUtil.publicEncrpyt(userJson, publicKeyStr);
        System.out.println("公钥分段加密 "+ publicEncrpyt);

        //获取私钥
        String privateKeyStr = RSAUtil.getPrivateKeyStr(keyPair);

        //私钥分段解密
        String privateDecrypt = RSAUtil.privateDecrypt(publicEncrpyt, privateKeyStr);
        System.out.println("私钥分段解密 "+privateDecrypt);
    }

    /**
     * 私钥解密
     */
    @Test
    public void test02(){
        //生成秘钥对
        KeyPair keyPair = null;
        try {
            //生成密钥对
            keyPair = RSAUtil.generateRSAKeyPair(1023);
            //String privateKeyStr = RSAUtil.getPrivateKeyStr(keyPair);
            String publicEncrpyt = "F5f9OMsnr0ZI+SCIwyz5M2ccMdz1g18xOC1/6ram5VHF1nK9JYFNtBHMoXbzQzTTkI+r4sOe3MI+AneNP+ooXm11/3TQZMLgY25rWR7jJ01AaSVYP7Q+cp3nsar4IgWFtfLhygOMyDHjfS0nzl3Besw065D5QXS6d+OdNliPF8wHA3EV9k9ucsNdA8d1m15O3dO3pTgvin7Ol7VlEG/SI7i+ZQu6jZPGEbnAiM+TL7LkRMbo+0dRwb3i2HhmZ6kvHEvWmaik5eE85fSppI1wzIi+5wMi1KZtF4nIAPCRbEWCbhjwaR3QyhqSNM0eb2JXOfZhPlsENd3Gdw0reZRvyg/YyPrzF7tyxaVJvkZeXcIRiFhkjNVF+8321WAiTnbfKW+qG6YH/NmrjESCN3ESZMdg+19YsBtI+iqzvia0fXUaHoFQHCzisOtjPRvt7ZhIPRRHWCbw+7hyu19cmMleUqOL/DIf/WfElhuldmMol0B6FWlxNKsspI1WiNSKBjeXFDwbSi4BASEPlvdBh/r+/pAocQ9/FWeXbiJCBihp96yWVZnWOaBfkeNqGgdpBm3brU++4G2bH7TLaii/3YTlxAe5apByCQsoEPzUOF40eqE+3nRwVSx6+JVmUbRbQe6sN/Nb43BYtFjKR87xDjfxtJLFVpwyqR4aqgA6t2EIZx8=";
            String privateDecrypt = RSAUtil.privateDecrypt(publicEncrpyt, Constants.privateKeyStr);
            System.out.println("私钥分段解密 " + privateDecrypt);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公私钥测试
     */
    @Test
    public void test03() throws Exception {
        //生成秘钥对
        KeyPair keyPair = RSAUtil.generateRSAKeyPair(1023);
        String publicKeyStr = RSAUtil.getPublicKeyStr(keyPair);
        //获取私钥
        String privateKeyStr = RSAUtil.getPrivateKeyStr(keyPair);
        System.out.println("公钥: " + publicKeyStr);
        System.out.println("私钥: " + privateKeyStr);

    }
}
