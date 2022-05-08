package com.ctgu.bs_hotel.repository;

import com.ctgu.bs_hotel.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
//<User, Long>前者代表要查哪个表，后者代表id的类型
//继承JpaRepository之后，会默认使用SimpleJpaRepository这个实现类作为UserRepository接口的实体bean
//继承JpaSpecificationExecutor是为了实现动态查询
public interface AdminRepository extends JpaRepository<Admin, Long>, JpaSpecificationExecutor<Admin> {

    Admin findByUsername(String username);

    Admin findByEmail(String email);

    Admin findByPhone(String phone);

    void deleteAllByIdIn(Set<Long> ids);

    @Query(value = "SELECT m.* FROM sys_user m WHERE " +
            "m.user_id = ?1",nativeQuery = true)
    Admin selectHotelIdByAdminId(int adminId);

    @Query(value = "SELECT m.* FROM sys_user m WHERE " +
            "m.user_id = ?1",nativeQuery = true)
    Admin resetPassword(Long adminId);


    @Query(value = "SELECT m.* FROM sys_user m WHERE " +
            "m.username like %?1%",nativeQuery = true)
    Page<Admin> findAdmin(String userName, Pageable pageable);

//    @Query(value = "select count(t.*) from sys_user t where t.username like %?1%",nativeQuery = true)
//    int findAdminLength(String userName);


    @Query(value = "SELECT count(m.*) FROM sys_user m WHERE " +
            "m.username like %?1%",nativeQuery = true)
    int findAdminLength(String userName);

    @Query(value = "SELECT m.username FROM sys_user m WHERE " +
            "m.hotel_id = ?1",nativeQuery = true)
    List<String> findNameByHotelId(String hotelId);
}
