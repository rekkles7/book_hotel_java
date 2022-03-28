package com.ctgu.bs_hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;

import java.util.List;

/**
 * ClassName HotelService
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:27 下午
 */
public interface OrderService extends IService<Order> {
    List<OrderVo> selectAllOrderByUserId(String userId);

    List<Order> selectToPayOrderByUserIdAndSelectedIndex(String userId,int selectedIndex);

}
