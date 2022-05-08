package com.ctgu.bs_hotel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * ClassName Comment
 * Description
 * Create by luochuang
 * Date 2022/5/1 1:30 下午
 */

@Data
@TableName("comments")
public class Comments {
    private static final long serialVersionUID = 1L;


    @TableId(value = "comment_id",type = IdType.AUTO)
    private int commentId;

    @TableField("open_id")
    private String openId;

    @TableField("comment_content")
    private String commentContent;

    @TableField("hotel_id")
    private int hotelId;

    @TableField("reply_content")
    private String replyContent;

    @TableField("order_id")
    private int orderId;

    @TableField("create_time")
    private Date createTime;

    public Comments(String openId, String commentContent, int hotelId, int orderId, Date createTime) {
        this.openId = openId;
        this.commentContent = commentContent;
        this.hotelId = hotelId;
        this.orderId = orderId;
        this.createTime = createTime;
    }

    public Comments() {
    }
}
