package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import com.sky.vo.ShoppingCartVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/07   18:00
 * @Version 1.0
 * @Description 购物车接口
 */
@Slf4j
@RestController
@Api(tags = "用户端-购物车接口")
@RequestMapping("/user/shoppingCart")
public class UserShoppingCartController {

    @Autowired
    private ShoppingCartService shopService;

    /**
     * 添加购物车
     * @param shoppingCartDTO 购物车信息
     * @return com.sky.result.Result
     * @author Aip
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加到购物车")
    public Result addToShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("UserShoppingCartController.addToShoppingCart(ShoppingCartDTO 加入购物车的商品信息 = {})", shoppingCartDTO);
        shopService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /**
     * 查询购物车列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询购物车列表")
    public Result<List<ShoppingCartVO>> queryShoppingCart() {
        log.info("UserShoppingCartController.queryShoppingCart()");
        return Result.success(shopService.queryShoppingCart());
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation(value = "清空购物车")
    public Result cleanShoppingCart() {
        log.info("UserShoppingCartController.cleanShoppingCart()");
        shopService.clearCart();
        return Result.success();
    }

    /**
     * 从购物车中删除商品
     * @param shoppingCartDTO 购物车信息
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation(value = "从购物车中删除商品")
    public Result subtractShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("UserShoppingCartController.subtractShoppingCart(ShoppingCartDTO 从购物车中删除的商品信息 = {})", shoppingCartDTO);
        shopService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }
}
