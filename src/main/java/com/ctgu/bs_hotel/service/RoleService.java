package com.ctgu.bs_hotel.service;

import com.ctgu.bs_hotel.service.dto.AdminDto;
import com.ctgu.bs_hotel.service.dto.small.RoleSmallDto;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * ClassName RoleService
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:54 下午
 */
public interface RoleService {
    /**
     * 根据用户ID查询
     * @param id 用户ID
     * @return /
     */
    List<RoleSmallDto> findByUsersId(Long id);

    /**
     * 获取用户权限信息
     * @param admin 用户信息
     * @return 权限信息
     */
    List<GrantedAuthority> mapToGrantedAuthorities(AdminDto admin);

    List<RoleSmallDto> queryAll();
}
