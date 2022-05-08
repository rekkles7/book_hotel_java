package com.ctgu.bs_hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bs_hotel.entity.Comments;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.entity.vo.AdminCommentsVo;
import com.ctgu.bs_hotel.entity.vo.CommentsVo;

import java.util.List;

/**
 * ClassName CommentsService
 * Description
 * Create by luochuang
 * Date 2022/5/1 1:38 下午
 */
public interface CommentsService extends IService<Comments> {
    int createComment(Comments comments);

    List<CommentsVo> selectCommentsByHotelId(int hotelId);

    void replyComment(int commentId, String replyContent);

    List<AdminCommentsVo> selectAdminCommentsByHotelId(int hotelId);
}
