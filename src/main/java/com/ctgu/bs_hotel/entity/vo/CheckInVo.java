package com.ctgu.bs_hotel.entity.vo;

import lombok.Data;

/**
 * ClassName CheckInVo
 * Description
 * Create by luochuang
 * Date 2022/5/4 10:57 上午
 */
@Data
public class CheckInVo {
    private int checkId;

    private String checkUsername;

    private String checkUserphone;

    private String checkIdCard;

    private String checkRoomNo;

    private int orderId;

    private int hotelId;

    private String startOfDate;

    private String endOfDate;
}
