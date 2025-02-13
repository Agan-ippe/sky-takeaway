package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author Aip
 * @Date 2025/01/24   16:52
 * @Version 1.0
 * @Description 详细订单Mapper接口
 */
@Mapper
public interface OrderItemMapper {

    void insertBatch(List<OrderDetail> orderDetailList);
}
