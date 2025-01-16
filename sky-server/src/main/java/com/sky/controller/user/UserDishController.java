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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate redisTemplate;

    static final String KEY = "category_";

    @GetMapping("/list")
    @ApiOperation("根据类型id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(KEY + categoryId);
        if (list != null && list.size() > 0) {
            return Result.success(list);
        }
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        list = dishService.listWithFlavor(dish);
        redisTemplate.opsForValue().set(KEY + categoryId, list, 8L, TimeUnit.HOURS);
        //TODO 使用redis 做缓存，将菜品数据放在redis中，以提升访问速度，设置数据过期时间，先给8小时
        //TODO 此处是通过分类id变化来存入菜品数据，此外每次菜品有更新操作，都需要重新存入数据，同样设置过期时间
        //TODO 延迟双删 
        return Result.success(list);
    }

}
