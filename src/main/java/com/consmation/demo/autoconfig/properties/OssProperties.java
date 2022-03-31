package com.consmation.demo.autoconfig.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:13:32
 */
@Data
@ConfigurationProperties(prefix="interface.oss")
public class OssProperties {

    private String accessKey;
    private String secret;
    private String bucketName;
    private String url; //域名
    private String endpoint;

}
