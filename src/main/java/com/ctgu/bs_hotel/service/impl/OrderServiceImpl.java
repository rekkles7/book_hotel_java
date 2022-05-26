package com.ctgu.bs_hotel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ctgu.bs_hotel.common.DateUtil;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.UserCenterOrderVo;
import com.ctgu.bs_hotel.mapper.OrderMapper;
import com.ctgu.bs_hotel.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public List<UserCenterOrderVo> selectAllOrderByUserId(String userId) {
        List<UserCenterOrderVo> orderList = new ArrayList<>();
        orderList = orderMapper.selectAllOrderByUserId(userId);
        for (UserCenterOrderVo userCenterOrderVo : orderList) {
            String  currentStartOfDate = userCenterOrderVo.getStartOfDate();
            String currentEndOfDate = userCenterOrderVo.getEndOfDate();
            userCenterOrderVo.setStartOfDate(DateUtil.getNowDateShort(currentStartOfDate));
            userCenterOrderVo.setEndOfDate(DateUtil.getNowDateShort(currentEndOfDate));
        }
        return orderList;
    }

    @Override
    public List<UserCenterOrderVo> selectToPayOrderByUserIdAndSelectedIndex(String userId, int selectedIndex) {
        List<UserCenterOrderVo> orderList = new ArrayList<>();
        orderList = orderMapper.selectToPayOrderByUserIdAndSelectedIndex(userId,selectedIndex);
        for (UserCenterOrderVo userCenterOrderVo : orderList) {
            String  currentStartOfDate = userCenterOrderVo.getStartOfDate();
            String currentEndOfDate = userCenterOrderVo.getEndOfDate();
            userCenterOrderVo.setStartOfDate(DateUtil.getNowDateShort(currentStartOfDate));
            userCenterOrderVo.setEndOfDate(DateUtil.getNowDateShort(currentEndOfDate));
        }
        return orderList;
    }

    @Override
    public List<OrderVo> selectAllOrderByHotelId(Long hotelId) {
        List<OrderVo> orderList = new ArrayList<>();
        orderList = orderMapper.selectAllOrderByHotelId(hotelId);
        return orderList;
    }

    @Override
    public int updateOrderNameAndTelphone(int orderId, String orderUserName, String orderUserTelephone,int orderStatus) {
        return orderMapper.updateOrderNameAndTelphone(orderId,orderUserName,orderUserTelephone,orderStatus);
    }

    @Override
    public int confirmOrder(Long id) {
        return orderMapper.confirmOrder(id);
    }

    @Override
    public int cancelOrder(Long id) {
        return orderMapper.cancelOrder(id);
    }

    @Override
    public int saveOrder(Order createOrder) {
        return orderMapper.saveOrder(createOrder);
    }

    @Override
    public Order selectOrderById(int orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public int toPayOrder(int orderId, Date date) {
        return orderMapper.toPayOrder(orderId,date);
    }

    @Override
    public void updateOrderStatusById(int orderId) {
        orderMapper.updateOrderStatusById(orderId);
    }

    @Override
    public Order selectOrderByIdDlx(int orderId) {
        return orderMapper.selectOrderByIdDlx(orderId);
    }

    @Override
    public Order selectExistOrder(int orderId) {
        return orderMapper.selectExistOrder(orderId);
    }

    @Override
    public IPage<OrderVo> selectPageVo(Page<OrderVo> page, Long hotelId, Integer orderStatus, String orderUserName) {
        return orderMapper.selectPageVo(page,hotelId,orderStatus,orderUserName);
    }

    @Override
    public void updateOrder(int orderId, String orderUserName, String orderUserTelephone, String orderUserPs) {
        orderMapper.updateOrder(orderId,orderUserName,orderUserTelephone,orderUserPs);
    }

    @Override
    public double calculateServiceCharge(int orderId) {
        Order order = orderMapper.selectExistOrder(orderId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sod = sdf.format(order.getStartOfDate());
        StringBuilder startOfDate = new StringBuilder(sod);
        startOfDate = startOfDate.replace(11,13,"18");
        sod = startOfDate.toString();
        String nd = sdf.format(new Date());
        String today = sod.substring(0,10);
        today = today + " 00:00:00";
        double rate = 0;
        System.out.println(sod);
        System.out.println(nd);
        System.out.println(today);
        if (nd.compareTo(today) < 0){
            rate = 0;
        }
        if(nd.compareTo(today) > 0 && nd.compareTo(sod) < 0){
            rate = 0.1;
        }
        return rate;
    }

}
