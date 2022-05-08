package com.ctgu.bs_hotel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.UserCenterOrderVo;

import java.util.Date;
import java.util.List;

/**
 * ClassName HotelService
 * Description
 * Create by luochuang
 * Date 2022/3/2 4:27 下午
 */
public interface OrderService extends IService<Order> {
    List<UserCenterOrderVo> selectAllOrderByUserId(String userId);

    List<UserCenterOrderVo> selectToPayOrderByUserIdAndSelectedIndex(String userId,int selectedIndex);

    List<OrderVo> selectAllOrderByHotelId(Long hotelId);

    int updateOrderNameAndTelphone(int orderId, String orderUserName, String orderUserTelephone,int orderStatus);

    int confirmOrder(Long id);

    int cancelOrder(Long id);

    int saveOrder(Order createOrder);

    Order selectOrderById(int orderId);

    int toPayOrder(int orderId, Date date);

    void updateOrderStatusById(int orderId);

    Order selectOrderByIdDlx(int orderId);

    Order selectExistOrder(int orderId);
}
