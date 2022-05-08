package com.ctgu.bs_hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.bs_hotel.entity.Comments;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.entity.vo.AdminCommentsVo;
import com.ctgu.bs_hotel.entity.vo.CommentsVo;
import com.ctgu.bs_hotel.mapper.CommentsMapper;
import com.ctgu.bs_hotel.mapper.HotelMapper;
import com.ctgu.bs_hotel.service.CommentsService;
import com.ctgu.bs_hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CommentsServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/5/1 1:38 下午
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public int createComment(Comments comments) {
        return commentsMapper.createComment(comments);
    }

    @Override
    public List<CommentsVo> selectCommentsByHotelId(int hotelId) {
        return commentsMapper.selectCommentsByHotelId(hotelId);
    }

    @Override
    public void replyComment(int commentId, String replyContent) {
        commentsMapper.replyComment(commentId,replyContent);
    }

    @Override
    public List<AdminCommentsVo> selectAdminCommentsByHotelId(int hotelId) {
        return commentsMapper.selectAdminCommentsByHotelId(hotelId);
    }
}
