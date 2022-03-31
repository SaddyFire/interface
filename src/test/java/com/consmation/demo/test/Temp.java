package com.consmation.demo.test;

import com.consmation.demo.InterfaceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author SaddyFire
 * @date 2022/3/31
 * @TIME:10:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InterfaceApplication.class)
public class Temp {
    @Test
    public void test(){
        System.out.println(12/7);
    }
}
