package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import nonapi.io.github.classgraph.utils.LogNode;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void insertDish(Dish dish);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO 查询条件
     * @return 菜品分页结果
     */
    Page<DishVO> queryDishPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据ID查询菜品
     * @param id 菜品ID
     */
    @Select("SELECT * FROM t_dish WHERE id = #{id}")
    Dish queryDishByID(Long id);

    /**
     * 根据ID批量删除
     * @param ids 菜品ID数组
     */
    void deleteBatchByIds(Long... ids);

    /**
     * 根据ID修改菜品
     * @param dish 菜品对象
     */
    @AutoFill(OperationType.UPDATE)
    void updateDish(Dish dish);

    /**
     * 根据ID修改菜品状态
     * @param dish 菜品对象
     */
    @AutoFill(OperationType.UPDATE)
    void updateDishStatus(Dish dish);
}
