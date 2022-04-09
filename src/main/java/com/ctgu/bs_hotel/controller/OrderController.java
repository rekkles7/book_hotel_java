package com.ctgu.bs_hotel.controller;

import com.ctgu.bs_hotel.common.DateUtil;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.vo.OrderMetaVo;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.UserCenterOrderVo;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
    public GlobalResult saveOrder(OrderMetaVo order) throws ParseException {
        Order createOrder = new Order();
        createOrder.setOpenId(order.getOpenId());
        createOrder.setOrderUserName(order.getOrderUserName());
        createOrder.setOrderUserTelephone(order.getOrderUserTelephone());
        createOrder.setStartOfDate(DateUtil.string2Date(order.getStartOfDate()+" 13:00:00"));
        createOrder.setEndOfDate(DateUtil.string2Date(order.getEndOfDate()+" 12:00:00"));
        createOrder.setHotelId(Integer.parseInt(order.getHotelId()));
        createOrder.setRoomId(Integer.parseInt(order.getRoomId()));
        createOrder.setOrderPrice(order.getOrderPrice());
        createOrder.setOrderUserPs(order.getOrderUserPs());
        createOrder.setOrderRoomNumber(order.getOrderRoomNumber());
        createOrder.setOrderCreateTime(new Date());
        createOrder.setOrderStatus(0);
        boolean create = orderService.saveOrder(createOrder);
        if (create) {
            return GlobalResult.ok();
        }else{
            return GlobalResult.build(500,"订单创建失败",null);
        }
    }

    @RequestMapping("/selectAllOrderByUserId")
    public GlobalResult selectAllOrderByUserId(@RequestParam("userId") String userId){
        List<UserCenterOrderVo> orderList = new ArrayList<>();
        orderList = orderService.selectAllOrderByUserId(userId);
        if (orderList == null){
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(orderList);
        }
    }

    @RequestMapping("selectToPayOrderByUserIdAndSelectedIndex")
    public GlobalResult selectToPayOrderByUserId(@RequestParam("userId") String userId,@RequestParam("selectedIndex")int selectedIndex){
        if (selectedIndex == 1){
            selectedIndex = selectedIndex - 1;
        }
        List<UserCenterOrderVo> orderList = new ArrayList<>();
        orderList = orderService.selectToPayOrderByUserIdAndSelectedIndex(userId,selectedIndex);
        if (orderList == null){
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(orderList);
        }
    }

    @RequestMapping("selectOrderById")
    public GlobalResult selectOrderById(@RequestParam("orderId") int orderId){
        Order order = orderService.selectOrderById(orderId);
        if (order == null){
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(order);
        }
    }

    @RequestMapping("toPayOrder")
    public GlobalResult toPayOrder(@RequestParam("orderId") int orderId){
        int n = orderService.toPayOrder(orderId,new Date());
        if (n == 0){
            return GlobalResult.build(500,"支付失败",null);
        }else{
            return GlobalResult.ok();
        }
    }

}
