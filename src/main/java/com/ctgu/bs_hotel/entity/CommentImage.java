package com.ctgu.bs_hotel.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * ClassName CommentImage
 * Description
 * Create by luochuang
 * Date 2022/5/3 2:40 下午
 */

@Data
@TableName("comment_image")
public class CommentImage {

    private int commentImageId;

    private int commentId;

    private String imageUrl;

    public CommentImage(int commentId, String imageUrl) {
        this.commentId = commentId;
        this.imageUrl = imageUrl;
    }
}
