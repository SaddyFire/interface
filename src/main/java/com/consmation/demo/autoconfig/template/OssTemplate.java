package com.consmation.demo.autoconfig.template;

import cn.hutool.crypto.SecureUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.consmation.demo.autoconfig.properties.OssProperties;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.consmation.demo.utils.Constants;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:13:45
 */
public class OssTemplate {

    private OssProperties ossProperties;

    public OssTemplate(OssProperties ossProperties) {
        this.ossProperties = ossProperties;
    }

    /**
     *
     * @param fileName 文件名
     * @param is 输入流
     * @return
     */
    public String upload(String fileName, InputStream is){
        //属性提取
        String endpoint = null;
        String accessKeyId = null;
        String secreAccessKey = null;
        String bucketName = null;
        //参数判断
        try {
            endpoint = ossProperties.getEndpoint();
            accessKeyId = ossProperties.getAccessKey();
            secreAccessKey = ossProperties.getSecret();
            bucketName = ossProperties.getBucketName();
        } catch (Exception e) {
            throw new CustomException(AppHttpCodeEnum.PARAM_REQUIRE);
        }

        //密码校验(小写32位)
        if (!SecureUtil.md5(Constants.aliAccessKeyId).equals(accessKeyId) || !SecureUtil.md5(Constants.aliSecret).equals(secreAccessKey)) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID);
        }

        //封装oos路径
        String storePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        System.out.println(storePath);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, Constants.aliAccessKeyId, Constants.aliSecret);
        try {
            ossClient.putObject(bucketName, storePath, is);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
        } finally {
            String url = ossProperties.getUrl() + storePath;
            if (ossClient != null) {
                ossClient.shutdown();
            }
            return url;
        }
    }
}
