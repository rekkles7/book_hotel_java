package com.ctgu.bs_hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.CosUtils;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.common.RedisUtils;
import com.ctgu.bs_hotel.entity.CommentImage;
import com.ctgu.bs_hotel.entity.Comments;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.entity.vo.CommentVo;
import com.ctgu.bs_hotel.entity.vo.CommentsVo;
import com.ctgu.bs_hotel.service.CommentImageService;
import com.ctgu.bs_hotel.service.CommentsService;
import com.ctgu.bs_hotel.service.HotelService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName CommentsController
 * Description
 * Create by luochuang
 * Date 2022/5/1 1:35 下午
 */
@RestController
@RequestMapping("/user/comments")
public class CommentsController {

    @Autowired
    private CommentImageService commentImageService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private HotelService hotelService;

    /**
     * 根据id查询评论
     * @param hotelId
     * @return
     */
    @GetMapping("/selectCommentsByHotelId")
    public GlobalResult selectCommentsByHotelId(@RequestParam("hotelId") int hotelId){
        List<CommentsVo> commentsVos = new ArrayList<>();
        commentsVos = commentsService.selectCommentsByHotelId(hotelId);
        for (CommentsVo commentsVo : commentsVos) {
            commentsVo.setStartOfDate(commentsVo.getStartOfDate().substring(0,7));
            commentsVo.setCommentCreateDate(commentsVo.getCommentCreateDate().substring(0,10));
        }
        return GlobalResult.build(200,"成功", commentsVos);
    }

    @PostMapping("/upload")
    public String uploadImg(@RequestParam("files") MultipartFile file) throws IllegalStateException, IOException {
        CosUtils client = new CosUtils();
        String url = client.uploadFile(file);
        System.out.println(file.getOriginalFilename() + "图片已传入!!");
        return url;
    }

    /**
     * 保存评论
     * @param commentVo
     * @return
     */
    @PostMapping("/saveComment")
    public GlobalResult saveComment(CommentVo commentVo){
        int orderId = commentVo.getOrderId();
        QueryWrapper<Comments> commentsQueryWrapper = new QueryWrapper<>();
        commentsQueryWrapper.eq("order_id",orderId);
        Comments exist = commentsService.getOne(commentsQueryWrapper);
        if (exist!=null){
            return GlobalResult.errorMsg("单个订单只能评论一次");
        }else{
            QueryWrapper<Hotel> hotelQueryWrapper = new QueryWrapper<Hotel>();
            hotelQueryWrapper.eq("hotel_name",commentVo.getHotelName());
            Hotel hotel = hotelService.getOne(hotelQueryWrapper);
            int hotelId = hotel.getHotelId();
            //先创建评论,得到评论的ID
            Comments comments = new Comments(commentVo.getOpenId(),commentVo.getText(),hotelId,commentVo.getOrderId(),new Date());
            int create= commentsService.createComment(comments);
            if (create != 0){
                // 创建成功
                List<String> urlList = commentVo.getUrls();
                for (String s : urlList) {
                    commentImageService.save(new CommentImage(comments.getCommentId(),s));
                }
                return GlobalResult.ok();
            }else{
                return GlobalResult.errorMsg("评论失败");
            }
        }
    }

}
