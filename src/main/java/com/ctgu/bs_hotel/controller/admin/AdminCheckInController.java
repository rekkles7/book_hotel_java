package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.CheckIn;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.CheckInVo;
import com.ctgu.bs_hotel.service.CheckInService;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName AdminCheckInController
 * Description
 * Create by luochuang
 * Date 2022/5/3 11:24 下午
 */
@RestController
@RequestMapping("/admin/checkin")
public class AdminCheckInController {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public GlobalResult saveCheckIn(@RequestBody CheckIn checkIn){
        Order order = orderService.selectExistOrder(checkIn.getOrderId());
        if (order == null ){
            return GlobalResult.errorMsg("订单号不存在");
        }else{
            int orderHotelId = order.getHotelId();
            int hotelId = checkIn.getHotelId();
            if(orderHotelId != hotelId){
                return GlobalResult.errorMsg("该订单不属于本酒店所有");
            }else{
                boolean save = checkInService.save(checkIn);
                if(save){
                    return GlobalResult.ok();
                }else{
                    return GlobalResult.build(500,"保存入住信息失败",null);
                }
            }
        }
    }

    @PutMapping
    public GlobalResult updateCheckIn(@RequestBody CheckIn checkIn){
        Order order = orderService.selectExistOrder(checkIn.getOrderId());
        if (order == null ){
            return GlobalResult.errorMsg("订单号不存在");
        }else{
            boolean update = checkInService.updateById(checkIn);
            if(update){
                return GlobalResult.ok();
            }else{
                return GlobalResult.build(500,"修改入住信息失败",null);
            }
        }
    }

    @RequestMapping("/selectCheckInByHotelId")
    public GlobalResult selectCheckIn(@RequestParam("hotelId")int hotelId){
        List<CheckInVo> checkInVoList = new ArrayList<>();
        checkInVoList = checkInService.selectCheckInByHotelId(hotelId);
        return GlobalResult.ok(checkInVoList);
    }



}
