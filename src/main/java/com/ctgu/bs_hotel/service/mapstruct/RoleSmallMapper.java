package com.ctgu.bs_hotel.service.mapstruct;


import com.ctgu.bs_hotel.base.BaseMapper;
import com.ctgu.bs_hotel.entity.Role;
import com.ctgu.bs_hotel.service.dto.small.RoleSmallDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Zheng Jie
 * @date 2019-5-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends BaseMapper<RoleSmallDto, Role> {

}
