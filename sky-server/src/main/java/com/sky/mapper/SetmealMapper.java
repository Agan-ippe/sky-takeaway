package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/10  17:58
 * @description 负责套餐相关的数据操作
 */
@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐数量
     * @param id
     * @return
     */
    @Select("SELECT COUNT(id) FROM t_setmeal WHERE category_id = #{categoryId}")
    Integer countByCategoryId(Long id);
}
