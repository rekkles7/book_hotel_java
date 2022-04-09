package com.ctgu.bs_hotel.entity.vo;

import lombok.Data;

/**
 * ClassName UserCenterOrderVo
 * Description
 * Create by luochuang
 * Date 2022/4/5 2:33 下午
 */
@Data
public class UserCenterOrderVo {
    private String hotelName;

    private int orderId;

    private String startOfDate;

    private String endOfDate;

    private int orderPrice;

    private String roomName;

    private int orderRoomNumber;

    private int orderStatus;

    private String roomService;

    private String roomImgUrl;
}
