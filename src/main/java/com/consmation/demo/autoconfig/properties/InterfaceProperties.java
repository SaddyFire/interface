package com.consmation.demo.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:17:58
 */
@Data
@ConfigurationProperties(prefix="interface")
public class InterfaceProperties {
    //白名单列表
    private List<String> whiteList;



}
