package com.sky.controller.user;

import com.sky.constant.StatusConstant;
import com.sky.entity.Dish;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Aip
 * @Date 2024/12/25   18:09
 * @Version 1.0
 * @Description 用户菜品相关接口
 */
@Slf4j
@RestController
@Api(tags = "用户端-菜品接口")
@RequestMapping("/user/dish")
public class UserDishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/list")
    @ApiOperation("根据类型id查询菜品")
    public Result<List<DishVO>> list(Long categoryId){
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        List<DishVO> list = dishService.listWithFlavor(dish);
        return Result.success(list);
    }

}
