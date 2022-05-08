package com.ctgu.bs_hotel.entity.vo;

import lombok.Data;

/**
 * ClassName AdminCommentsVo
 * Description
 * Create by luochuang
 * Date 2022/5/6 10:37 上午
 */
@Data
public class AdminCommentsVo {

    private int commentId;

    private String createTime;

    private String nickName;

    private String replyContent;

    private String roomName;

    private String avatarUrl;

    private String commentContent;
}
