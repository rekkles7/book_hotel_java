package com.ctgu.bs_hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.Room;

import java.text.ParseException;
import java.util.List;

/**
 * ClassName HotelService
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:27 下午
 */
public interface RoomService extends IService<Room> {
    List<Room> findRoomByDate(String startOfDate, String endOfDate, int hotelId) throws ParseException;

    int findMinPrice(int hotelId);
}
