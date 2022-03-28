package com.ctgu.bs_hotel.controller;

import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName OrderController
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:17 下午
 */
@RestController
@RequestMapping("/user/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // todo
    @RequestMapping("/saveOrder")
    public GlobalResult saveOrder(Order order){

        return GlobalResult.ok();
    }

    @RequestMapping("/selectAllOrderByUserId")
    public GlobalResult selectAllOrderByUserId(@RequestParam("userId") String userId){
        List<OrderVo> orderList = new ArrayList<>();
        orderList = orderService.selectAllOrderByUserId(userId);
        System.out.println(orderList);
        if (orderList == null){
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(orderList);
        }
    }

    @RequestMapping("selectToPayOrderByUserIdAndSelectedIndex")
    public GlobalResult selectToPayOrderByUserId(@RequestParam("userId") String userId,@RequestParam("selectedIndex")int selectedIndex){
        List<Order> orderList = new ArrayList<>();
        orderList = orderService.selectToPayOrderByUserIdAndSelectedIndex(userId,selectedIndex);
        if (orderList == null){
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(orderList);
        }
    }

}
