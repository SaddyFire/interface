package com.consmation.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T> implements Serializable {
    private Long total = 0l;//总记录数
    private Long size = 10l;//页大小
    private Long current = 0l;//当前页
    private List<?> records = Collections.emptyList(); //列表
    private String dataEncrpyt;    //加密信息
    public PageResult(Long page, Long size,
                      Long total, List list) {
        this.size = size;
        this.records = list;
        this.total = total;
        this.current = total % size == 0 ? total / size : total / size + 1;
    }
}
