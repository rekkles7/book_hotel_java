package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.mapper.OrderMapper;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName AdminOrderController
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:22 下午
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/selectAllOrderByHotelId")
    public GlobalResult selectAllOrderByAdminId(@RequestParam("hotelId") int hotelId){
        List<Order> orderList = new ArrayList<>();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("hotel_id",hotelId);
        orderList = orderService.list(queryWrapper);
        if (orderList == null) {
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(orderList);
        }
    }

}
