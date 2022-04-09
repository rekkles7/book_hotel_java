package com.ctgu.bs_hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * ClassName Order
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:12 下午
 */
@Data
@TableName("order")
public class Order {
    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id",type = IdType.AUTO)
    private int orderId;

    @TableField("open_id")
    private String openId;

    @TableField("start_of_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startOfDate;

    @TableField("end_of_date")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endOfDate;

    @TableField("order_status")
    private int orderStatus;

    @TableField("order_price")
    private int orderPrice;

    @TableField("hotel_id")
    private int hotelId;

    @TableField("room_id")
    private int roomId;

    @TableField("order_user_name")
    private String orderUserName;

    @TableField("order_user_telephone")
    private String orderUserTelephone;

    @TableField("order_user_ps")
    private String orderUserPs;

    @TableField("order_room_number")
    private int orderRoomNumber;

    @TableField("order_create_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderCreateTime;

    @TableField("order_pay_time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderPayTime;
}
