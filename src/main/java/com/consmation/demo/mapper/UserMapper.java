package com.consmation.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.consmation.demo.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:42
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select \"id\",\"name\",\"age\"\n" +
            "from \"INTERFACE1\".\"user\" limit #{current},#{size}")
    List<User> getUserList(@Param("current") Long current,@Param("size") Long size);

    @Select("select count(id) from \"INTERFACE1\".\"user\"")
    Long getCount();

}
