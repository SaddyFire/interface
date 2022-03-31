package com.consmation.demo.stragy.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.consmation.demo.anno.RSAEncrpyt;
import com.consmation.demo.mapper.UserMapper;
import com.consmation.demo.model.enums.AppHttpCodeEnum;
import com.consmation.demo.model.pojo.User;
import com.consmation.demo.model.vo.PageResult;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.stragy.InvokeStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author SaddyFire
 * @date 2022/3/24
 */
@Slf4j
@Component("type2")
public class InvokeStrategyType2 implements InvokeStrategyService {

    @Autowired
    private UserMapper userMapper;


    @Override
    @RSAEncrpyt
    public ResponseResult invoke(Long current, Long size) {
        //sql语句实现
        List<User> userList = userMapper.getUserList(current,size);
        Long count = userMapper.getCount();
        PageResult<Object> pageResult = PageResult.builder()
                .current(current)
                .size(size)
                .total(count)
                .records(userList).build();
        log.info("type2执行");


        return ResponseResult.okResult(pageResult);
    }
}
