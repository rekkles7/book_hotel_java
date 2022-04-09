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
        //sod eod 分别为用户指定的起始时间和结束时间
        Date sod = DateUtil.string2Date(startOfDate + " 13:00:00");
        Date eod = DateUtil.string2Date(endOfDate + " 12:00:00");
        //查出该酒店所有的订单信息
        List<Order> orderList= roomMapper.findRoomByDate(hotelId);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("hotel_id",hotelId);
        //查出该酒店所有的房型
        List<Room> roomList = roomMapper.selectList(wrapper);
        //遍历订单
        for (Order order : orderList) {
            //判断两个日期之间是否有交集
            if (DateUtil.isCross(sod,eod,order.getStartOfDate(),order.getEndOfDate())){
                //如果有的话，havabooked为当前订单的房间总数
                int havaBooked = order.getOrderRoomNumber();
                //找出order的房型id，然后拿房型的number减去havabooked
                for (Room room : roomList) {
                    if(order.getRoomId() == room.getRoomId()){
                        room.setRoomNumber(room.getRoomNumber()-havaBooked);
                    }
                }
            }
        }
        return roomList;
    }

    @Override
    public int findMinPrice(int hotelId) {
        return roomMapper.findMinPrice(hotelId);
    }
}
