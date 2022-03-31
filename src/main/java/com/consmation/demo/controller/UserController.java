package com.consmation.demo.controller;

import com.consmation.demo.anno.RSAEncrpyt;
import com.consmation.demo.model.dto.UserDto;
import com.consmation.demo.model.vo.ResponseResult;
import com.consmation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author SaddyFire
 * @date 2022/3/29
 * @TIME:17:50
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //新增接口
    @PostMapping()
    public ResponseResult addData(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    //删除
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }
    //修改
    @PutMapping
    public ResponseResult modifyById(@RequestBody UserDto userDto){
        return userService.modifyById(userDto);
    }

    //根据id查找
    @RSAEncrpyt
    @GetMapping("/{id}")
    public ResponseResult findById(@PathVariable("id")Long id){
        return userService.findById(id);
    }

}
