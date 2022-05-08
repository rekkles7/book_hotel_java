package com.ctgu.bs_hotel.common;

import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.mapper.OrderMapper;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName TimeoutOrderListener
 * Description
 * Create by luochuang
 * Date 2022/4/11 2:01 下午
 */
@Component
public class TimeoutOrderListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = "q.order.dlx")
    public void onMessage(int orderId)  {
        //通过从死信队列中获取的订单号，查询订单
        Order order = orderService.selectOrderByIdDlx(orderId);
        System.out.println(order.toString());
        //判断订单状态如果为未支付，则修改状态为过期
        if(order.getOrderStatus() == 0) {
            orderService.updateOrderStatusById(order.getOrderId());
        }

    }

}

