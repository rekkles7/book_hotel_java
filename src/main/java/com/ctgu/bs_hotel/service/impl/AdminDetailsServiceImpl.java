package com.ctgu.bs_hotel.service.impl;

import com.ctgu.bs_hotel.service.AdminService;
import com.ctgu.bs_hotel.service.RoleService;
import com.ctgu.bs_hotel.service.dto.AdminDto;
import com.ctgu.bs_hotel.service.dto.JwtAdminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ClassName AdminDetailsServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:46 下午
 */
@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminDto user = adminService.findByName(username);
        JwtAdminDto jwtAdminDto = new JwtAdminDto(
                user,
                //获取当前用户的数据权限，并进行授权
                null,
                //获取当前用户的权限，并进行授权
                roleService.mapToGrantedAuthorities(user)
        );
        return jwtAdminDto;
    }
}
