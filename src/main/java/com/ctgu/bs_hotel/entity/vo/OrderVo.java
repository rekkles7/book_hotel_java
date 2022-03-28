package com.ctgu.bs_hotel.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * ClassName orderDTO
 * Description
 * Create by luochuang
 * Date 2022/3/9 2:14 下午
 */
@Data
public class OrderVo {

    private String hotelName;

    private String roomName;

    private String roomImgUrl;

    private String roomService;

    private String startOfDate;

    private String endOfDate;

    private int orderStatus;

    private int orderId;

    private int orderPrice;

    private String orderUserName;

    private String orderUserTelephone;

    private String orderUserPs;

    private int orderRoomNumber;

    private Date orderCreateTime;

    private Date orderPayTime;

}
