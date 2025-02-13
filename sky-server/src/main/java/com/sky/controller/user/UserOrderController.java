package com.sky.controller.user;

import com.sky.dto.OrdersPaymentDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.entity.OrderDetail;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Aip
 * @Date 2025/01/19   15:46
 * @Version 1.0
 * @Description 用户订单相关接口
 */

@Slf4j
@RestController
@Api(tags = "用户订单相关接口")
@RequestMapping("/user/order")
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 提交订单
     * @param submitDTO 提交DTO
     * @return com.sky.result.Result<com.sky.vo.OrderSubmitVO>
     */
    @PostMapping("/submit")
    @ApiOperation("提交订单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO submitDTO) {
        log.info("UserOrderController.submit:{}", submitDTO);
        OrderSubmitVO submitVO = orderService.submit(submitDTO);
        return Result.success(submitVO);
    }

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付：{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单：{}", orderPaymentVO);
        orderService.paySuccess(ordersPaymentDTO.getOrderNumber());
        return Result.success(orderPaymentVO);
    }


    /**
     * 查询历史订单
     * @return com.sky.result.Result<com.sky.result.PageResult>
     * @author Aip
     */
//    @GetMapping("/historyOrders")
//    @ApiOperation("查询历史订单")
//    public Result<List<>> queryHistoryOrders() {
//        log.info("UserOrderController.queryHistoryOrders +++ 查询历史订单");
//        return Result.success(orderService.queryHistoryOrders());
//    }


    @GetMapping("/orderDetail/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderDetail> queryOrderItemByOrderID(@PathVariable(value = "id") Long OrderID) {
        return Result.success();
    }

    @GetMapping("/reminder/{id}")
    @ApiOperation("催单")
    public Result reminderOrder(@PathVariable(value = "id") Long OrderID) {

        return Result.success();
    }

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result cancelOrder(@PathVariable(value = "id") Long OrderID) {

        return Result.success();
    }


    @PostMapping("/repetition/{id}")
    @ApiOperation("再来一单")
    public Result repurchaseByOrderID(@PathVariable(value = "id") Long OrderID) {

        return Result.success();
    }
}
