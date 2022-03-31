package com.consmation.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    private Long id;
    private String name;
    private Integer age;
}
