package com.ctgu.bs_hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName Hotel
 * Description
 * Create by luochuang
 * Date 2022/2/24 1:35 下午
 */
@Data
@TableName("hotel")
public class Hotel {
    private static final long serialVersionUID = 1L;


    @TableId(value = "hotel_id",type = IdType.AUTO)
    private int hotelId;

    @TableField("hotel_name")
    private String hotelName;

    @TableField("hotel_score")
    private double hotelScore;

    @TableField("hotel_description")
    private String hotelDescription;

    @TableField("hotel_price")
    private int hotelPrice;

    @TableField("hotel_img_url")
    private String hotelImgUrl;

    @TableField("hotel_address")
    private String hotelAddress;

    @TableField("hotel_service")
    private String hotelService;

}
