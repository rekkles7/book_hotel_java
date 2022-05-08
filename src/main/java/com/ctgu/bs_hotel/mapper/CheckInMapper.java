package com.ctgu.bs_hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgu.bs_hotel.entity.CheckIn;
import com.ctgu.bs_hotel.entity.vo.CheckInVo;

import java.util.List;

/**
 * ClassName CheckInMapper
 * Description
 * Create by luochuang
 * Date 2022/5/3 11:20 下午
 */
public interface CheckInMapper extends BaseMapper<CheckIn> {
    List<CheckInVo> selectCheckInByHotelId(int hotelId);
}
