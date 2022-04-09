package com.ctgu.bs_hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bs_hotel.entity.Hotel;

/**
 * ClassName HotelService
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:27 下午
 */
public interface HotelService extends IService<Hotel> {
    void setMinPrice(int hotelId,int minPrice);
}
