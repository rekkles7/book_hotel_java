package com.ctgu.bs_hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bs_hotel.entity.CheckIn;
import com.ctgu.bs_hotel.entity.CommentImage;
import com.ctgu.bs_hotel.entity.vo.CheckInVo;

import java.util.List;

/**
 * ClassName CheckInService
 * Description
 * Create by luochuang
 * Date 2022/5/3 11:19 下午
 */
public interface CheckInService extends IService<CheckIn> {
    List<CheckInVo> selectCheckInByHotelId(int hotelId);
}
