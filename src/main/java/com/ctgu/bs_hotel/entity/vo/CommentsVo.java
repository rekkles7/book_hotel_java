package com.ctgu.bs_hotel.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ctgu.bs_hotel.entity.CommentImage;
import lombok.Data;

import java.util.List;

/**
 * ClassName CommentsVo
 * Description 酒店评价展示
 * Create by luochuang
 * Date 2022/5/1 3:49 下午
 */
@Data
public class CommentsVo {

    @TableField("comment_id")
    private int commentId;

    @TableField("nick_name")
    private String nickName;

    @TableField("avatar_url")
    private String avatarUrl;

    @TableField("start_of_date")
    private String startOfDate;

    @TableField("create_time")
    private String commentCreateDate;

    @TableField("room_name")
    private String roomName;

    @TableField("comment_content")
    private String commentContent;

    @TableField("reply_content")
    private String replyContent;

    private List<CommentImage> imageUrls;
}

