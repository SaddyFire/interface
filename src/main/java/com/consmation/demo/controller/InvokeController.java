package com.consmation.demo.controller;

import com.consmation.demo.anno.AccessLimit;
import com.consmation.demo.autoconfig.properties.InterfaceProperties;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.model.dto.UserDto;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.service.OssService;
import com.consmation.demo.service.UserService;
import com.consmation.demo.stragy.InvokeStrategyService;
import com.consmation.demo.stragy.InvokeContext;
import com.consmation.demo.utils.Constants;
import com.consmation.demo.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;


/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:24
 */
@RestController
@RequestMapping("/demo")
public class InvokeController {

    @Autowired
    private InvokeContext invokeContext;

    @Autowired
    private OssService ossService;

    //根据传参不同策略至不同实现类
    @GetMapping("/invoke")
    public ResponseResult getData(@RequestParam(defaultValue = "1") Long current,
                     @RequestParam(defaultValue = "10") Long size,
                     String type){
        InvokeStrategyService invokeServiceImpl = null;
        try {
            //通过策略上下文拿到策略实现类
            invokeServiceImpl = invokeContext.getResource(type);
        } catch (Exception e) {
            throw new CustomException(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        return invokeServiceImpl.invoke(current, size);
    }

    //测试数据解密接口
    @GetMapping("/decrpytTest")
    public ResponseResult decryptTest(@RequestHeader("keyStr") String keyStr){
        String privateDecrypt;
        try {
            privateDecrypt = RSAUtil.privateDecrypt(keyStr, Constants.privateKeyStr);
            System.out.println(privateDecrypt);
        } catch (Exception e) {
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
        }
        return ResponseResult.okResult(privateDecrypt);
    }


    //oos上传文件
    @PostMapping("/uploadoss")
    public ResponseResult uploadOss(MultipartFile multipartFile){
        return ossService.upload(multipartFile);
    }


    @Autowired
    private InterfaceProperties interfaceProperties;

    @GetMapping("/limit")
    @AccessLimit(QPS = 3,acquireTokenTimeout = 5,timeunit = TimeUnit.MILLISECONDS)
    public ResponseResult limitTest(){
        System.out.println(interfaceProperties.getWhiteList());
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}

