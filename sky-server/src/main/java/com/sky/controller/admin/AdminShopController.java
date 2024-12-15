package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Aip
 * @Date 2024/12/09   17:31
 * @Version 1.0
 * @Description 商家端店铺管理
 */
@RestController
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class AdminShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    public final static String KEY = "SHOP_STATUS";

    /**
     * @author Aip
     * @param status
     * @return com.sky.result.Result
     * @description 设置店铺的营业状态
     */
    @PutMapping("/{status}")
    @ApiOperation("商家端设置店铺的营业状态")
    public Result setStatus(@PathVariable Integer status){
        log.info("已修改当前店铺状态为: {}", status == 1 ? "营业中" : "休息中");
        redisTemplate.opsForValue().set(KEY, status);
        return Result.success();
    }

    /**
     * @author Aip
     * @return com.sky.result.Result<java.lang.Integer>
     * @description 获取店铺的营业状态
     */
    @GetMapping("/status")
    @ApiOperation("商家端获取店铺的营业状态")
    public Result<Integer> getStatus(){
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        System.out.println(shopStatus);
        log.info("获取当前店铺状态为: {}", shopStatus == 1 ? "营业中" : "休息中");
        return Result.success(shopStatus);
    }
}
