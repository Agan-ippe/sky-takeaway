package com.sky.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class ShoppingCartDTO implements Serializable {

    /**
     * 菜品id
     */
    private Long dishId;

    /**
     * 套餐id 这个不需要
     */
    private Long setmealId;

    /**
     * 口味
     */
    private String dishFlavor;

}
