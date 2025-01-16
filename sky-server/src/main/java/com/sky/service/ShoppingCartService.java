package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.vo.ShoppingCartVO;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/07   18:02
 * @Version 1.0
 * @Description 购物车业务接口
 */

public interface ShoppingCartService {

    /**
     * 添加购物车
     * @author Aip
     * @param shoppingCartDTO 购物车信息
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查询购物车列表
     * @return
     */
    List<ShoppingCartVO> queryShoppingCart();

    /**
     * 从购物车中减去一个商品
     * @param shoppingCartDTO 购物车信息
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 清空购物车
     */
    void clearCart();
}
