package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Aip
 * @Date 2025/01/14   18:09
 * @Version 1.0
 * @Description 购物车VO
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartVO {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品展示图
     */
    private String image;

    /**
     * 商品规格
     */
    private String dishFlavor;

    /**
     * 商品数量
     */
    private Integer number;

    /**
     * 商品价格
     */
    private BigDecimal amount;
}
