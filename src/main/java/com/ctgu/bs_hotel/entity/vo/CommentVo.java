package com.ctgu.bs_hotel.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * ClassName CommentVo
 * Description 用户发起评论
 * Create by luochuang
 * Date 2022/5/1 2:17 下午
 */
@Data
public class CommentVo {

    private String openId;

    private String hotelName;

    private int orderId;

    private String text;

    private List<String> urls;

}
