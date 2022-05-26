package com.ctgu.bs_hotel.controller;

import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Room;
import com.ctgu.bs_hotel.service.OrderService;
import com.ctgu.bs_hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName RoomController
 * Description
 * Create by luochuang
 * Date 2022/3/1 12:26 下午
 */
@RestController
@RequestMapping("/user/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderService orderService;

    /**
     * 根据起始时间和结束时间实时对房间剩余库存进行筛查 查看是否有房源
     * @param hotelId 酒店id
     * @param startOfDate 房间起始时间
     * @param endOfDate 房间结束时间
     * @return
     * @throws ParseException
     */
    @RequestMapping("/selectRoomByHotelId")
    public GlobalResult selectRoomByHotelId(@RequestParam("hotelId") int hotelId, @RequestParam(value = "startOfDate")String startOfDate,
                                            @RequestParam(value = "endOfDate")String endOfDate) throws ParseException {
        List<Room> roomList = new ArrayList<>();
        roomList = roomService.findRoomByDate(startOfDate,endOfDate,hotelId);
        if (roomList == null) {
            return GlobalResult.build(500,"暂时没有房型数据",null);
        }else {
            for (Room room : roomList) {
                System.out.println(room.toString());
            }
            return GlobalResult.ok(roomList);
        }
    }
}
