package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/10  17:57
 * @description 负责菜品表相关的数据操作
 */
@Mapper
public interface DishMapper {

    /**
     * 根据分类ID查询菜品数量
     * @param categoryId 分类ID
     */
    @Select("SELECT COUNT(id) FROM t_dish WHERE category_id = #{categoryId}")
    Integer countByCategoryID(Long categoryId);


}
