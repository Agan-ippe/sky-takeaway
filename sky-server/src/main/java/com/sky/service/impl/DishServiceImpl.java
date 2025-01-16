package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/27  17:08
 * @description
 */
@Slf4j
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper flavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增菜品
     * @param dishDTO
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class) // 事务管理
    public void insertDish(DishDTO dishDTO) {
        try {
            Dish dish = new Dish();
            BeanUtils.copyProperties(dishDTO, dish);
            // 向菜品表插入数据
            dishMapper.insertDish(dish);

            Long dishId = dish.getId();
            // 向口味表插入数据
            List<DishFlavor> flavors = dishDTO.getFlavors();
            if (flavors != null && flavors.size() > 0) {
                flavors.forEach(dishFlavor -> {
                    dishFlavor.setDishId(dishId);
                });
                // 批量插入
                flavorMapper.insertBatch(flavors);
            }
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO 查询条件
     * @return 分页结果
     */
    @Override
    public PageResult queryDishPage(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.queryDishPage(dishPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 根据id删除菜品
     * @param ids 菜品id
     */
    @Override
    @Transactional
    public void deleteDishByID(Long... ids) {
        // 判断当前是否为起售菜品
        for (Long id : ids) {
            Dish dish = dishMapper.queryDishByID(id);
            if(dish.getStatus().equals(StatusConstant.ENABLE)){
                // 起售菜品不能删除
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 判断当前菜品是否被套餐关联
        List<Long> setmealIds = setmealDishMapper.getSetmealIdByDishId(ids);
        if (setmealIds != null && setmealIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        // 批量删除菜品数据
        dishMapper.deleteBatchByIds(ids);
        log.info("删除菜品成功");
        flavorMapper.deleteBatchByIds(ids);
        log.info("删除菜品口味成功");
    }

    /**
     * 根据id查询菜品和口味
     * @param dishId 菜品id
     */
    @Override
    public DishVO queryDishAndFlavorById(Long dishId) {
        Dish dish = dishMapper.queryDishByID(dishId);
        List<DishFlavor> flavors = flavorMapper.queryFlavorByDishId(dishId);
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(flavors);
        return dishVO;
    }

    /**
     * 修改菜品状态
     * @param id 菜品id
     * @param status 菜品状态
     */
    @Override
    public void updateDishStatus(Long id, Integer status) {
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.updateDishStatus(dish);
    }

    /**
     * 修改菜品
     * @param dishDTO 菜品信息
     */
    @Override
    public void updateDishAndFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.updateDish(dish);
        flavorMapper.deleteBatchByIds(dishDTO.getId());

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            flavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 根据菜品类型查询菜品
     * @param dish 菜品信息
     */
    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.queryDishList(dish);
        ArrayList<DishVO> dishVoList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            // 根据菜品id查询对应口味
            List<DishFlavor> flavors = flavorMapper.queryFlavorByDishId(d.getId());
            dishVO.setFlavors(flavors);
            dishVoList.add(dishVO);
        }
        return dishVoList;
    }

    @Override
    public List<Dish> QueryDishListByCategoryId(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return dishMapper.queryDishList(dish);
    }
}
