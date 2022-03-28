package com.ctgu.bs_hotel.repository;

import com.ctgu.bs_hotel.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
