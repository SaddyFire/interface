package com.consmation.demo.stragy.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.consmation.demo.anno.RSAEncrpyt;
import com.consmation.demo.mapper.UserMapper;
import com.consmation.demo.model.pojo.User;
import com.consmation.demo.model.vo.PageResult;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.stragy.InvokeStrategyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:43
 */
@Component("type1")
@Slf4j
public class InvokeStrategyType1 implements InvokeStrategyService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @RSAEncrpyt
    public ResponseResult invoke(Long current, Long size) {
        IPage<User> iPage = new Page<>(current, size);
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        IPage<User> userIPage = userMapper.selectPage(iPage, qw);
        PageResult pageResult = PageResult.builder()
                //总记录数
                .total(userIPage.getTotal())
                //页大小
                .size(userIPage.getSize())
                //总页数
                .current(userIPage.getCurrent())
                //列表
                .records(userIPage.getRecords())
                .build();

        log.info("type1执行");
        return ResponseResult.okResult(pageResult);
    }
}
