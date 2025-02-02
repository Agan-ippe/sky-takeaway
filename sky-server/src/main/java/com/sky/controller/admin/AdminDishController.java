package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aip
 * @version 1.0
 * @date 2024/9/19  10:54
 * @description
 */
@Slf4j
@RestController
@Api(tags = "菜品管理接口")
@RequestMapping("/admin/dish")
public class AdminDishController {

    @Autowired
    private DishService dishService;

    @PostMapping
    @ApiOperation("新增菜品")
    @CachePut
    public Result<String> insertDish(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.insertDish(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询菜品")
    public Result<PageResult> queryDishPage(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.queryDishPage(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @DeleteMapping
    @ApiOperation("删除菜品")
    public Result<String> deleteDish(Long... ids){
        log.info("删除菜品:{}", ids);
        dishService.deleteDishByID(ids);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("修改菜品")

    public Result<String> updateDish(@RequestBody DishDTO dishDTO){
        // 业务需求，先查询菜品信息:菜品、口味、所属分类，前端进行数据渲染，再提交修改
        log.info("修改菜品:{}", dishDTO);
        dishService.updateDishAndFlavor(dishDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改菜品售卖状态")
    public Result<String> updateDishStatus(@PathVariable Integer status, Long id){
        log.info("修改菜品状态:{}", status);
        dishService.updateDishStatus(id,status);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> queryDishById(@PathVariable Long id){
        log.info("根据id查询菜品:{}", id);
        return Result.success(dishService.queryDishAndFlavorById(id));
    }

    @GetMapping("/list")
    @ApiOperation("查询菜品列表")
    @Cacheable(value = "dishCache",key = "#categoryId")
    public Result<List<Dish>> queryDishList(Long categoryId){
        List<Dish> dishList = dishService.QueryDishListByCategoryId(categoryId);
        return Result.success(dishList);
    }

}
