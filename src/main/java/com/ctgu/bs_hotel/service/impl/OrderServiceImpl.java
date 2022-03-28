package com.ctgu.bs_hotel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.bs_hotel.common.DateUtil;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.mapper.OrderMapper;
import com.ctgu.bs_hotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName HotelServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:32 下午
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderVo> selectAllOrderByUserId(String userId) {
        List<OrderVo> orderList = new ArrayList<>();
        orderList = orderMapper.selectAllOrderByUserId(userId);
        for (OrderVo orderVo : orderList) {
            String  currentStartOfDate = orderVo.getStartOfDate();
            String currentEndOfDate = orderVo.getEndOfDate();
            orderVo.setStartOfDate(DateUtil.getNowDateShort(currentStartOfDate));
            orderVo.setEndOfDate(DateUtil.getNowDateShort(currentEndOfDate));
        }
        return orderList;
    }

    @Override
    public List<Order> selectToPayOrderByUserIdAndSelectedIndex(String userId, int selectedIndex) {
        List<Order> orderList = new ArrayList<>();
        orderList = orderMapper.selectToPayOrderByUserIdAndSelectedIndex(userId,selectedIndex);
        return orderList;
    }

}
