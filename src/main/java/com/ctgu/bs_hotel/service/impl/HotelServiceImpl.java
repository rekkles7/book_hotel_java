package com.ctgu.bs_hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.mapper.HotelMapper;
import com.ctgu.bs_hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName HotelServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:32 下午
 */
@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public void setMinPrice(int hotelId,int minPrice) {
        hotelMapper.setMinPrice(hotelId,minPrice);
    }
}
