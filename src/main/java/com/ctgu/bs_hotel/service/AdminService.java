package com.ctgu.bs_hotel.service;

import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.service.dto.AdminDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * ClassName AdminService
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:23 下午
 */
@Service
public interface AdminService {
    AdminDto findByName(String userName);

    Object queryAll(Pageable pageable);

    void create(Admin resources);

    void update(Admin resources) throws Exception;

    void delete(Set<Long> ids);

    Admin selectHotelIdByAdminId(int adminId);

    Admin resetPassword(Long id);

    Page<Admin> findAdmin(String userName, Pageable pageable);

    int findLength();

    List<String> findNameByHotelId(String hotelId);
}
