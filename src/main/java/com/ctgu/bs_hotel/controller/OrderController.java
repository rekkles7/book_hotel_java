package com.ctgu.bs_hotel.controller;

import com.ctgu.bs_hotel.common.DateUtil;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.common.RedisUtils;
import com.ctgu.bs_hotel.common.WebSocket;
import com.ctgu.bs_hotel.entity.Room;
import com.ctgu.bs_hotel.entity.vo.OrderMetaVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.UserCenterOrderVo;
import com.ctgu.bs_hotel.service.AdminService;
import com.ctgu.bs_hotel.service.OrderService;
import com.ctgu.bs_hotel.service.RoomService;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private RedisUtils redisUtils;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private WebSocket webSocket;



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
        createOrder.setOrderPrice(order.getOrderPrice()*order.getOrderRoomNumber());
        createOrder.setOrderUserPs(order.getOrderUserPs());
        createOrder.setOrderRoomNumber(order.getOrderRoomNumber());
        createOrder.setOrderCreateTime(new Date());
        createOrder.setOrderStatus(0);
        Room room = roomService.getById(Integer.parseInt(order.getRoomId()));
        int newVersion = room.getVersion();
        System.out.println("newVersion="+newVersion+"oldVersion"+order.getVersion());
        if (newVersion == order.getVersion()){
            int create = orderService.saveOrder(createOrder);
            roomService.updateById(room);
            if (create != 0) {
                redisUtils.set(String.valueOf(createOrder.getOrderId()),"waitToPay",900, TimeUnit.SECONDS);
                rabbitTemplate.convertAndSend("ex.order", "order",createOrder.getOrderId());
                return GlobalResult.ok();
            }else{
                return GlobalResult.build(500,"订单创建失败",null);
            }
        }else{
            return GlobalResult.build(500,"订单创建失败",null);
        }
    }

    @RequestMapping("/getKeyExpireTime")
    public GlobalResult getKeyExpireTime(@RequestParam("orderId") int orderId){
        long expire = redisUtils.getExpire(Integer.toString(orderId));
        if (expire != -1){
            return GlobalResult.ok(expire);
        }else {
            return GlobalResult.build(500,"key过期",null);
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
        Order order = orderService.selectOrderById(orderId);
        List<String> userNameList = new ArrayList<>();
        int n = orderService.toPayOrder(orderId,new Date());
        if (n == 0){
            return GlobalResult.build(500,"支付失败",null);
        }else{
            userNameList = adminService.findNameByHotelId(Integer.toString(order.getHotelId()));
            System.out.println(userNameList.toString());
            for (String s : userNameList) {
                webSocket.sendOneMessage(s,"您有新的订单，请及时确认");
            }
            redisUtils.del(Integer.toString(orderId));
            return GlobalResult.ok();
        }
    }

    @RequestMapping("updateOrder")
    public GlobalResult updateOrder(@RequestParam("orderId") int orderId,
                                    @RequestParam("orderUserName") String orderUserName,
                                    @RequestParam("orderUserTelephone") String orderUserTelephone,
                                    @RequestParam("orderUserPs") String orderUserPs){
        orderService.updateOrder(orderId,orderUserName,orderUserTelephone,orderUserPs);
        return GlobalResult.ok();
    }

    @RequestMapping("calculateServiceCharge")
    public GlobalResult calculateServiceCharge(@RequestParam("orderId") int orderId){
        double rate = orderService.calculateServiceCharge(orderId);
        return GlobalResult.ok(rate);
    }
//    @RequestMapping("cancelO")



}
