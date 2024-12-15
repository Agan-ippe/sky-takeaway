package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Aip
 * @Date 2024/12/09   18:02
 * @Version 1.0
 * @Description 用户端店铺服务
 */
@RestController
@RequestMapping("/user/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class UserShopController {

    @Autowired
    private RedisTemplate redisTemplate;

    public final static String KEY = "SHOP_STATUS";

    /**
     * @author Aip
     * @return com.sky.result.Result<java.lang.Integer>
     * @description 获取店铺的营业状态
     */
    @GetMapping("/status")
    @ApiOperation("用户端获取店铺的营业状态")
    public Result<Integer> getStatus(){
        // TODO 优化常量
        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获取当前店铺状态为: {}", shopStatus == 1 ? "营业中" : "休息中");
        return Result.success(shopStatus);
    }
}
