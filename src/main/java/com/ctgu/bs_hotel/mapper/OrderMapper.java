package com.ctgu.bs_hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import com.ctgu.bs_hotel.entity.vo.UserCenterOrderVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName OrderMapper
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:19 下午
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<UserCenterOrderVo> selectAllOrderByUserId(@Param("userId") String userId);

    List<UserCenterOrderVo> selectToPayOrderByUserIdAndSelectedIndex(@Param("userId") String userId,@Param("selectedIndex")int selectedIndex);

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
