package com.consmation.demo.service.impl;

import com.consmation.demo.autoconfig.template.OssTemplate;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:14:52
 */
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssTemplate ossTemplate;

    @Override
    public ResponseResult upload(MultipartFile multipartFile) {
        String originalFilename;
        InputStream inputStream;
        try {
            //参数校验
            originalFilename = multipartFile.getOriginalFilename();
            inputStream = multipartFile.getInputStream();
        }catch (MaxUploadSizeExceededException maxUploadSizeExceededException){
            throw new CustomException(AppHttpCodeEnum.PICTURE_MAX);
        } catch (IOException e) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID);
        }
        String url = ossTemplate.upload(originalFilename, inputStream);
        //url校验
        if (StringUtils.isEmpty(url)) {
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR);
        }

        return ResponseResult.okResult(url);
    }
}
