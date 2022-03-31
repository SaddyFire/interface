package com.consmation.demo.aop;

import com.alibaba.fastjson.JSON;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.utils.Constants;
import com.consmation.demo.utils.RSAUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author SaddyFire
 * @date 2022/3/25
 */
@Aspect
@Component
public class RSAEncrpytAop {
    @Around("@annotation(com.consmation.demo.anno.RSAEncrpyt)")
    public Object around(ProceedingJoinPoint pjp){
        Object proceed = null;
        try {
            proceed =pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            //判断是否为ResponseResult
            if (!(proceed instanceof ResponseResult)) {
                return proceed;
            }
            //获取结果
            ResponseResult returnResult = (ResponseResult) proceed;
            //获取Data
            Object returnDataObj = returnResult.getData();
            //转json
            String returnDataJson = JSON.toJSONString(returnDataObj);

            try {
                //公钥分段加密
                String publicEncrpyt = RSAUtil.publicEncrpyt(returnDataJson, Constants.publicKeyStr);
                //封装returnResult
                returnResult.setData(publicEncrpyt);

            } catch (Exception e) {
                throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
            }
            return returnResult;
        }
    }

}
