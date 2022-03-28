package com.ctgu.bs_hotel.service.mapstruct;


import com.ctgu.bs_hotel.base.BaseMapper;
import com.ctgu.bs_hotel.entity.Menu;
import com.ctgu.bs_hotel.service.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper extends BaseMapper<MenuDto, Menu> {

}
