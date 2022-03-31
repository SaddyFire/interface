package com.consmation.demo.exception;

import com.consmation.demo.model.enums.AppHttpCodeEnum;

/**
 * @author SaddyFire
 * @date 2022/3/25
 * 业务异常
 */
public class CustomException extends RuntimeException {

    private AppHttpCodeEnum appHttpCodeEnum;

    public CustomException(AppHttpCodeEnum appHttpCodeEnum){
        this.appHttpCodeEnum = appHttpCodeEnum;
    }

    public AppHttpCodeEnum getAppHttpCodeEnum() {
        return appHttpCodeEnum;
    }
}