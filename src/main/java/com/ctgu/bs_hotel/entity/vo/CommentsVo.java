package com.ctgu.bs_hotel.entity.vo;

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

    private int commentId;

    private String nickName;

    private String avatarUrl;

    private String startOfDate;

    private String commentCreateDate;

    private String roomName;

    private String commentContent;

    private String replyContent;

    private List<CommentImage> imageUrls;
}
