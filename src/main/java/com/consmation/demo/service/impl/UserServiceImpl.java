package com.consmation.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.consmation.demo.exception.CustomException;
import com.consmation.demo.mapper.UserMapper;
import com.consmation.demo.model.dto.UserDto;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.consmation.demo.model.pojo.User;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * @author SaddyFire
 * @date 2022/3/27
 * @TIME:21:22
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseResult addUser(UserDto userDto) {
        //非空判断
        if (ObjectUtils.isEmpty(userDto)){
            throw new CustomException(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        User user;
        try {
            user = User.builder().name(userDto.getName()).age(userDto.getAge()).build();
        } catch (Exception e) {
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID);
        }
        userMapper.insert(user);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult delete(Long id) {
        int i = userMapper.deleteById(id);
        return i>0?ResponseResult.okResult(AppHttpCodeEnum.SUCCESS)
                :ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
    }

    @Override
    public ResponseResult findById(Long id) {
        User user = userMapper.selectById(id);
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        return ResponseResult.okResult(user);
    }

    @Override
    public ResponseResult modifyById(UserDto userDto) {
        if (ObjectUtils.isEmpty(userDto)) {
            throw new CustomException(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        //深拷贝
        User user = JSON.parseObject(JSON.toJSONString(userDto), User.class);
        int i = userMapper.updateById(user);
        return i>0?ResponseResult.okResult(AppHttpCodeEnum.SUCCESS)
                :ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
    }
}
