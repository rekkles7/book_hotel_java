package com.ctgu.bs_hotel.entity.vo;

import lombok.Data;

/**
 * ClassName OrderMetaVo
 * Description
 * Create by luochuang
 * Date 2022/4/3 10:27 上午
 */
@Data
public class OrderMetaVo {

    private String openId;

    private String orderUserName;

    private String orderUserTelephone;

    private String startOfDate;

    private String endOfDate;

    private String hotelId;

    private String roomId;

    private int orderPrice;

    private String orderUserPs;

    private int orderRoomNumber;
}
