package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单提交返回值
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSubmitVO implements Serializable {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单提交时间
     */
    private LocalDateTime orderTime;
}
