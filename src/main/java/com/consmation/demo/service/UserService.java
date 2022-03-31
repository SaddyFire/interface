package com.consmation.demo.service;

import com.consmation.demo.model.dto.UserDto;
import com.consmation.demo.model.vo.ResponseResult;

/**
 * @author SaddyFire
 * @date 2022/3/27
 * @TIME:21:20
 */
public interface UserService {
    /**
     * 添加用户
     * @param userDto
     * @return
     */
    ResponseResult addUser(UserDto userDto);

    /**
     * 删除用户
     * @param id
     * @return
     */
    ResponseResult delete(Long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ResponseResult findById(Long id);

    /**
     * 根据id修改
     * @param
     * @return
     */
    ResponseResult modifyById(UserDto userDto);
}
