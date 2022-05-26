package com.ctgu.bs_hotel.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * ClassName Room
 * Description
 * Create by luochuang
 * Date 2022/3/1 12:56 下午
 */
@Data
@TableName("room")
public class Room {
    private static final long serialVersionUID = 1L;


    @TableId(value = "room_id",type = IdType.AUTO)
    private int roomId;

    @TableField("room_name")
    private String roomName;

    @TableField("room_img_url")
    private String roomImgUrl;

    @TableField("room_number")
    private int roomNumber;

    @TableField("room_service")
    private String roomService;

    @TableField("room_price")
    private int roomPrice;

    @TableField("hotel_id")
    private int hotelId;

    @Version
    private int version;

}
