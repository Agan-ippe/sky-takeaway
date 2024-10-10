package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/27  17:27
 * @description
 */
@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品id批量删除口味
     * @param dishIds 菜品id
     */
    void deleteBatchByIds(Long... dishIds);

    /**
     * 根据菜品id查询口味
     * @param dishId 菜品id
     * @return 口味列表
     */
    @Select("SELECT * FROM t_flavor WHERE dish_id = #{dishId}")
    List<DishFlavor> queryFlavorByDishId(Long dishId);

    /**
     * 根据菜品id删除口味
     * @param id 菜品id
     */
//    @Delete("DELETE FROM t_flavor WHERE dish_id = #{id}")
//    void deleteDishById(Long id);
}
