package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Comments;
import com.ctgu.bs_hotel.entity.vo.AdminCommentsVo;
import com.ctgu.bs_hotel.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName AdminCommentsController
 * Description
 * Create by luochuang
 * Date 2022/5/1 1:36 下午
 */
@RestController
@RequestMapping("/admin/comments")
public class AdminCommentsController {

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/replyComment")
    public GlobalResult replyComment(@RequestParam("commentId")int commentId,@RequestParam("replyContent")String replyContent){
        Comments comments = commentsService.getById(commentId);
        System.out.println(comments.toString());
        if (!(comments.getReplyContent() == null)){
            return GlobalResult.errorMsg("已经回复过该评论，无需继续回复！");
        }else{
            commentsService.replyComment(commentId,replyContent);
            return GlobalResult.ok();
        }
    }

    @RequestMapping("/selectCommentsByHotelId")
    public GlobalResult selectCommentsByHotelId(@RequestParam("hotelId") int hotelId){
        List<AdminCommentsVo> adminCommentsVos = new ArrayList<>();
        adminCommentsVos = commentsService.selectAdminCommentsByHotelId(hotelId);
        for (AdminCommentsVo adminCommentsVo : adminCommentsVos) {
            adminCommentsVo.setCreateTime(adminCommentsVo.getCreateTime().substring(0,10));
        }
        return GlobalResult.ok(adminCommentsVos);
    }
}
