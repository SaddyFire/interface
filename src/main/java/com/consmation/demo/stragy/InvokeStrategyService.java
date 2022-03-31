package com.consmation.demo.stragy;

import com.consmation.demo.model.vo.ResponseResult;

/**
 * @author SaddyFire
 * @date 2022/3/24
 * @TIME:18:38
 */
public interface InvokeStrategyService {
    /**
     * 分页查询
     * @param current
     * @param size
     * @return
     */
    ResponseResult invoke(Long current, Long size);
}
