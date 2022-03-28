package com.ctgu.bs_hotel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ctgu.bs_hotel.entity.vo.OrderVo;
import com.ctgu.bs_hotel.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName OrderMapper
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:19 下午
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<OrderVo> selectAllOrderByUserId(@Param("userId") String userId);

    List<Order> selectToPayOrderByUserIdAndSelectedIndex(@Param("userId") String userId,@Param("selectedIndex")int selectedIndex);

}
