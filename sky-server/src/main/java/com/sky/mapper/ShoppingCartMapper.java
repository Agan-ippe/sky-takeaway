package com.sky.mapper;

import com.sky.entity.ShoppingCart;
import com.sky.vo.ShoppingCartVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/07   18:05
 * @Version 1.0
 * @Description
 */
@Mapper
public interface ShoppingCartMapper {

    /**
     * 动态条件查询购物车
     * @param shoppingCart 购物车信息
     * @return
     */
    List<ShoppingCart> queryShoppingCart(ShoppingCart shoppingCart);

    /**
     * 添加购物车中商品数量
     * @param cart 购物车对象
     */
    @Update("UPDATE t_shopping_cart SET `number` = #{number} WHERE id = #{id}")
    void updateNumberByID(ShoppingCart cart);

    /**
     * 添加购物车
     * @param shoppingCart 购物车对象
     */
    @Insert("INSERT INTO t_shopping_cart (`name`, image, user_id, dish_id, dish_flavor, `number`, amount, create_time) " +
            "VALUES (#{name}, #{image}, #{userId}, #{dishId}, #{dishFlavor}, #{number}, #{amount}, #{createTime})")
    void insertShoppingCart(ShoppingCart shoppingCart);

    /**
     * 查询购物车列表
     * @return
     */
    @Select("SELECT `name`,`image`,dish_flavor,`number`,amount FROM t_shopping_cart")
    List<ShoppingCartVO> queryShoppingCartList();

    /**
     * 从购物车中删除一个或多个商品
     * @param id 商品id
     */
    void deleteDishOnCartByID(Long id);

    @Delete("DELETE FROM t_shopping_cart WHERE user_id = #{id}")
    void cleanTheCart(Long id);

}
