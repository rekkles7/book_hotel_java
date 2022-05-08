package com.ctgu.bs_hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName CheckIn
 * Description
 * Create by luochuang
 * Date 2022/5/3 11:15 下午
 */
@Data
@TableName("check_in")
public class CheckIn {

    @TableId(value = "check_id",type = IdType.AUTO)
    private int checkId;

    private String checkUsername;

    private String checkUserphone;

    private String checkIdCard;

    private String checkRoomNo;

    private int orderId;

    private int hotelId;
}
