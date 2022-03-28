package com.ctgu.bs_hotel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.DateUtil;
import com.ctgu.bs_hotel.entity.Room;
import com.ctgu.bs_hotel.entity.User;
import com.ctgu.bs_hotel.mapper.RoomMapper;
import com.ctgu.bs_hotel.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class BsHotelApplicationTests {

    @Autowired
    private RoomMapper roomMapper;

    @Test
    void contextLoads() throws ParseException {
//        List<Room> roomList = new ArrayList<>();
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("hotel_id",1);
//        roomList = roomMapper.selectList(wrapper);
    }

}
