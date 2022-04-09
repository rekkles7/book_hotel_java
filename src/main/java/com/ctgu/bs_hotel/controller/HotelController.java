package com.ctgu.bs_hotel.controller;

import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.mapper.HotelMapper;
import com.ctgu.bs_hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName HotelController
 * Description
 * Create by luochuang
 * Date 2022/2/24 1:46 下午
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotel/selectAllHotel")
    public GlobalResult selectAllHotel(){
        List<Hotel> hotelList = new ArrayList<>();
        hotelList = hotelService.list();
        if (hotelList == null){
            return GlobalResult.build(500,"暂时没有酒店数据",null);
        }else{
            return GlobalResult.ok(hotelList);
        }
    }



}
