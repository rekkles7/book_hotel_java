package com.ctgu.bs_hotel.service.impl;

import com.ctgu.bs_hotel.common.PageUtil;
import com.ctgu.bs_hotel.common.ValidationUtil;
import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.exception.EntityExistException;
import com.ctgu.bs_hotel.repository.AdminRepository;
import com.ctgu.bs_hotel.service.AdminService;
import com.ctgu.bs_hotel.service.dto.AdminDto;
import com.ctgu.bs_hotel.service.mapstruct.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * ClassName AdminServiceImpl
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:24 下午
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminDto findByName(String userName) {
        Admin admin = adminRepository.findByUsername(userName);
        return adminMapper.toDto(admin);
    }

    @Override
    public Object queryAll(Pageable pageable) {
        Page<Admin> page = adminRepository.findAll(pageable);
        return PageUtil.toPage(page.map(adminMapper::toDto));
    }

    @Override
    public void create(Admin resources) {
        if (adminRepository.findByUsername(resources.getUsername()) != null) {
            throw new EntityExistException(Admin.class, "username", resources.getUsername());
        }
        if (adminRepository.findByEmail(resources.getEmail()) != null) {
            throw new EntityExistException(Admin.class, "email", resources.getEmail());
        }
        if (adminRepository.findByPhone(resources.getPhone()) != null) {
            throw new EntityExistException(Admin.class, "phone", resources.getPhone());
        }
        adminRepository.save(resources);
    }


    @Override
    public void update(Admin resources) throws Exception {
        Admin user = adminRepository.findById(resources.getId()).orElseGet(Admin::new);
        ValidationUtil.isNull(user.getId(), "User", "id", resources.getId());
        Admin user1 = adminRepository.findByUsername(resources.getUsername());
        Admin user2 = adminRepository.findByEmail(resources.getEmail());
        Admin user3 = adminRepository.findByPhone(resources.getPhone());
        if (user1 != null && !user.getId().equals(user1.getId())) {
            throw new EntityExistException(Admin.class, "username", resources.getUsername());
        }
        if (user2 != null && !user.getId().equals(user2.getId())) {
            throw new EntityExistException(Admin.class, "email", resources.getEmail());
        }
        if (user3 != null && !user.getId().equals(user3.getId())) {
            throw new EntityExistException(Admin.class, "phone", resources.getPhone());
        }
//        // 如果用户的角色改变
//        if (!resources.getRoles().equals(user.getRoles())) {
//            redisUtils.del(CacheKey.DATA_USER + resources.getId());
//            redisUtils.del(CacheKey.MENU_USER + resources.getId());
//            redisUtils.del(CacheKey.ROLE_AUTH + resources.getId());
//        }
//        // 如果用户被禁用，则清除用户登录信息
//        if(!resources.getEnabled()){
//            onlineUserService.kickOutForUsername(resources.getUsername());
//        }
        user.setUsername(resources.getUsername());
        user.setEmail(resources.getEmail());
        user.setEnabled(resources.getEnabled());
        user.setRoles(resources.getRoles());
        user.setPhone(resources.getPhone());
        user.setNickName(resources.getNickName());
        user.setGender(resources.getGender());
        adminRepository.save(user);
//        // 清除缓存
//        delCaches(user.getId(), user.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
//        for (Long id : ids) {
//            // 清理缓存
//            UserDto user = findById(id);
//            delCaches(user.getId(), user.getUsername());
//        }
        adminRepository.deleteAllByIdIn(ids);
    }

    @Override
    public Admin selectHotelIdByAdminId(int adminId) {
        return adminRepository.selectHotelIdByAdminId(adminId);
    }

}
