package com.ctgu.bs_hotel.service.mapstruct;


import com.ctgu.bs_hotel.base.BaseMapper;
import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.service.dto.AdminDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdminMapper extends BaseMapper<AdminDto, Admin> {

}
