package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.service.AdminService;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AdminService adminService;

    @RequestMapping("/selectAllOrderByAdminId")
    public ResponseEntity<Object> selectAllOrderByAdminId(@RequestParam(value = "adminId",required = true) Integer adminId,
                                                @RequestParam(value = "orderStatus",required = false)Integer orderStatus,
                                                @RequestParam(value = "orderUserName",required = false) String orderUserName,
                                                @RequestParam(value = "pageIndex",required = false)Integer pageIndex,
                                                @RequestParam(value = "pageSize",required = false)Integer pageSize){
        Admin admin = adminService.selectHotelIdByAdminId(adminId);
        Page<OrderVo> page = new Page<>(pageIndex, pageSize);
        IPage<OrderVo> orderVoIPage = orderService.selectPageVo(page,admin.getHotelId(),orderStatus,orderUserName);
//        for (OrderVo orderVo : orderList) {
//            orderVo.setStartOfDate(orderVo.getStartOfDate().substring(0,10));
//            orderVo.setEndOfDate(orderVo.getEndOfDate().substring(0,10));
//        }
        if (orderVoIPage == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(orderVoIPage, HttpStatus.OK);
        }
    }

    @PostMapping("/updateOrder")
    public GlobalResult updateOrder(@RequestBody Order order){
        System.out.println(order.toString());
        int n = orderService.updateOrderNameAndTelphone(order.getOrderId(),order.getOrderUserName(),order.getOrderUserTelephone(),order.getOrderStatus());
        if (n != 0){
            return GlobalResult.ok();
        }else{
            return GlobalResult.build(500,"修改失败",null);
        }
    }

}
