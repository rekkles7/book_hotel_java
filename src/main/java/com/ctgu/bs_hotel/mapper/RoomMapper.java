package com.ctgu.bs_hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName RoomMapper
 * Description
 * Create by luochuang
 * Date 2022/3/1 1:00 下午
 */
public interface RoomMapper extends BaseMapper<Room> {

    List<Order> findRoomByDate(@Param("hotelId") int hotelId);

    int findMinPrice(int hotelId);
}
