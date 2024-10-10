package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/29  18:13
 * @description
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询套餐id
     * @param dishIds 菜品ID
     * @return
     */
    List<Long> getSetmealIdByDishId(Long... dishIds);
}
