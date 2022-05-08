package com.ctgu.bs_hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgu.bs_hotel.entity.Comments;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.AdminCommentsVo;
import com.ctgu.bs_hotel.entity.vo.CommentsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName CommentsMapper
 * Description
 * Create by luochuang
 * Date 2022/5/1 1:39 下午
 */
public interface CommentsMapper extends BaseMapper<Comments> {

    int createComment(Comments comments);

    List<CommentsVo> selectCommentsByHotelId(@Param("hotelId") int hotelId);

    void replyComment(@Param("commentId") int commentId,@Param("replyContent") String replyContent);

    List<AdminCommentsVo> selectAdminCommentsByHotelId(@Param("hotelId")int hotelId);
}
