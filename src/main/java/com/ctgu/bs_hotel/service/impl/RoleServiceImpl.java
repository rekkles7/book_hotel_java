package com.ctgu.bs_hotel.service.impl;

import com.ctgu.bs_hotel.entity.Menu;
import com.ctgu.bs_hotel.entity.Role;
import com.ctgu.bs_hotel.repository.RoleRepository;
import com.ctgu.bs_hotel.service.RoleService;
import com.ctgu.bs_hotel.service.dto.AdminDto;
import com.ctgu.bs_hotel.service.dto.small.RoleSmallDto;
import com.ctgu.bs_hotel.service.mapstruct.RoleSmallMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleSmallMapper roleSmallMapper;


    @Override
    public List<RoleSmallDto> findByUsersId(Long id) {
        return roleSmallMapper.toDto(new ArrayList<>(roleRepository.findByUserId(id)));
    }

    @Override
    public List<GrantedAuthority> mapToGrantedAuthorities(AdminDto admin) {
        Set<String> permissions = new HashSet<>();
        // 如果是管理员直接返回
        if (admin.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        Set<Role> roles = roleRepository.findByUserId(admin.getId());
        //roles.stream(): 角色的流 stream(role role role ...)
        //roles.stream().map: 菜单的流的流     stream(steam(menus) steam(menus) steam(menus)...)
        //roles.stream().flatMap: 菜单的流    stream(menu menu menu ...)
        //map跟前端原理一样，把一个流变成另外一个流。
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNotBlank(menu.getPermission()))
                .map(Menu::getPermission).collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleSmallDto> queryAll() {
        Sort sort = Sort.by(Sort.Direction.ASC, "level");
        return roleSmallMapper.toDto(roleRepository.findAll(sort));
    }

}
