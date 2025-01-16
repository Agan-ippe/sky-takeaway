package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import com.sky.vo.ShoppingCartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/07   18:03
 * @Version 1.0
 * @Description 购物车相关业务逻辑实现类
 */

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shopMapper;
    @Autowired
    private DishMapper dishMapper;

    /**
     * 添加购物车
     * @param shoppingCartDTO 购物车信息
     */
    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        //查询当前购物车是否有该商品
        List<ShoppingCart> carts = shopMapper.queryShoppingCart(shoppingCart);
        if (carts != null && carts.size() > 0){
            ShoppingCart cart = carts.get(0);
            cart.setNumber(cart.getNumber() + 1);
            shopMapper.updateNumberByID(cart);
        }else {
            // 没有则添加
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null){
                // 通过查询到的菜品消息构造新的购物车对象
                Dish dish = dishMapper.queryDishByID(dishId);
                shoppingCart.setName(dish.getName());
                shoppingCart.setImage(dish.getImage());
                shoppingCart.setAmount(dish.getPrice());
            }
        }
        shoppingCart.setNumber(1);
        shoppingCart.setCreateTime(LocalDateTime.now());
        shopMapper.insertShoppingCart(shoppingCart);
    }

    /**
     * 查询购物车列表
     * @return
     */
    @Override
    public List<ShoppingCartVO> queryShoppingCart() {
        List<ShoppingCartVO> carts = shopMapper.queryShoppingCartList();
        return carts;
    }

    /**
     * 从购物车中减去一个商品
     * @param shoppingCartDTO 购物车信息
     */
    @Override
    public void subShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO,shoppingCart);
        // 设置查询条件，查询当前登录用户的购物车数据
        shoppingCart.setUserId(BaseContext.getCurrentId());

        List<ShoppingCart> list = shopMapper.queryShoppingCart(shoppingCart);

        if(list != null && list.size() > 0){
            shoppingCart = list.get(0);
            Integer number = shoppingCart.getNumber();
            if(number == 1){
                // 当前商品在购物车中的份数为1，直接删除当前记录
                shopMapper.deleteDishOnCartByID(shoppingCart.getId());
            }else {
                // 当前商品在购物车中的份数不为1，修改份数即可
                shoppingCart.setNumber(shoppingCart.getNumber() - 1);
                shopMapper.updateNumberByID(shoppingCart);
            }
        }
    }

    /**
     * 清空购物车
     */
    @Override
    public void clearCart() {
        Long id = BaseContext.getCurrentId();
        shopMapper.cleanTheCart(id);
    }
}
