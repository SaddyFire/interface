package com.consmation.demo.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasePojo {
    private Long id;
    private String name;
    private Integer age;
}
