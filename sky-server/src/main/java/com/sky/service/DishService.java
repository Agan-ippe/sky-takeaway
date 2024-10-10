package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/19  10:58
 * @description 菜品业务接口
 */
public interface DishService {

    /**
     * 新增菜品
     * @param dishDTO
     */
    void insertDish(DishDTO dishDTO);

    /**
     * 根据id删除菜品
     * @param ids 菜品id
     */
    void deleteDishByID(Long... ids);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO 查询条件
     * @return 分页结果
     */
    PageResult queryDishPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 修改菜品
     * @param dishDTO 菜品信息
     */
    void updateDishAndFlavor(DishDTO dishDTO);

    /**
     * 根据id查询菜品和口味
     * @param dishId 菜品id
     * @return 菜品信息
     */
    DishVO queryDishAndFlavorById(Long dishId);

    /**
     * 根据id修改菜品状态
     * @param id 菜品id
     * @param status 菜品状态
     */
    void updateDishStatus(Long id, Integer status);
}
