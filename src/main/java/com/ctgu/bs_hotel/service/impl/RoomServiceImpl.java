package com.ctgu.bs_hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.bs_hotel.common.DateUtil;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.Room;
import com.ctgu.bs_hotel.mapper.HotelMapper;
import com.ctgu.bs_hotel.mapper.RoomMapper;
import com.ctgu.bs_hotel.service.HotelService;
import com.ctgu.bs_hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName HotelServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:32 下午
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<Room> findRoomByDate(String startOfDate, String endOfDate, int hotelId) throws ParseException {
        Date sod = DateUtil.string2Date(startOfDate + " 13:00:00");
        Date eod = DateUtil.string2Date(endOfDate + " 13:00:00");
        List<Order> orderList= roomMapper.findRoomByDate(hotelId);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("hotel_id",hotelId);
        List<Room> roomList = roomMapper.selectList(wrapper);
        for (Order order : orderList) {
            if (DateUtil.isCross(sod,eod,order.getStartOfDate(),order.getEndOfDate())){
                int nowNumber = roomList.get(order.getRoomId()-1).getRoomNumber();
                roomList.get(order.getRoomId()-1).setRoomNumber(nowNumber-1);
            }
        }
        return roomList;
    }
}
