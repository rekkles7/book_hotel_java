package com.ctgu.bs_hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.bs_hotel.entity.CheckIn;
import com.ctgu.bs_hotel.entity.vo.CheckInVo;
import com.ctgu.bs_hotel.mapper.CheckInMapper;
import com.ctgu.bs_hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName CheckInServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/5/3 11:20 下午
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements CheckInService {

    @Autowired
    private CheckInMapper checkInMapper;

    @Override
    public List<CheckInVo> selectCheckInByHotelId(int hotelId) {
        return checkInMapper.selectCheckInByHotelId(hotelId);
    }
}
