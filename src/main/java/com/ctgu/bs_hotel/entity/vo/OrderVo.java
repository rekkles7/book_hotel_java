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

    private int orderId;

    private String orderUserName;

    private String orderUserTelephone;

    private String startOfDate;

    private String endOfDate;

    private int orderPrice;

    private String orderUserPs;

    private String roomName;

    private int orderRoomNumber;

    private int orderStatus;

    private String orderCreateTime;

    private String orderPayTime;

}
