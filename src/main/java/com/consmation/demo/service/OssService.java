package com.consmation.demo.service;

import com.consmation.demo.model.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SaddyFire
 * @date 2022/3/28
 * @TIME:14:51
 */
public interface OssService {
    /**
     * oss 上传文件
     * @param multipartFile
     * @return
     */
    ResponseResult upload(MultipartFile multipartFile);
}
