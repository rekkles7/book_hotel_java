package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.mapper.OrderMapper;
import com.ctgu.bs_hotel.service.AdminService;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private AdminService adminService;

    @RequestMapping("/selectAllOrderByAdminId")
    public GlobalResult selectAllOrderByAdminId(@RequestParam("adminId") int adminId){
        Admin admin = adminService.selectHotelIdByAdminId(adminId);
        List<OrderVo> orderList = new ArrayList<>();
        orderList = orderService.selectAllOrderByHotelId(admin.getHotelId());
        if (orderList == null) {
            return GlobalResult.build(500,"暂时没有订单数据",null);
        }else{
            return GlobalResult.ok(orderList);
        }
    }

    @PostMapping("/updateOrder")
    public GlobalResult updateOrder(@RequestBody Order order){
        int n = orderService.updateOrderNameAndTelphone(order.getOrderId(),order.getOrderUserName(),order.getOrderUserTelephone(),order.getOrderStatus());
        if (n != 0){
            return GlobalResult.ok();
        }else{
            return GlobalResult.build(500,"修改失败",null);
        }
    }

}
