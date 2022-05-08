package com.ctgu.bs_hotel.controller.admin;

import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.service.AdminService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * ClassName AdminUserController
 * Description
 * Create by luochuang
 * Date 2022/3/26 2:55 下午
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @ApiOperation("查询用户")
    @GetMapping
    public ResponseEntity<Object> queryUser(Pageable pageable) {
        System.out.println(pageable);
        return new ResponseEntity<>(adminService.queryAll(pageable), HttpStatus.OK);
    }

    @ApiOperation("查询用户")
    @GetMapping("selectUser")
    public ResponseEntity<Object> selectUser(@RequestParam(value = "userName")String userName,
                                             @RequestParam("pageSize") int pageSize,
                                             @RequestParam("pageIndex") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        Map<String,Object> objectMap = new HashMap<>();
        if (userName == null||"".equals(userName)){
            Object userList = adminService.queryAll(pageable);
            int length = adminService.findLength();
            objectMap.put("length",length);
            objectMap.put("userList",userList);
            return new ResponseEntity<>(objectMap, HttpStatus.OK);
        }else{
            Page<Admin> userList = adminService.findAdmin(userName,pageable);
            long length = userList.getTotalElements();
            System.out.println(length);
            objectMap.put("length",length);
            objectMap.put("userList",userList);
            return new ResponseEntity<>(objectMap, HttpStatus.OK);
        }
    }

    @ApiOperation("新增用户")
    @PostMapping
    public ResponseEntity<Object> createUser(@Validated @RequestBody Admin resources){
        // 默认密码 123456
        System.out.println(resources);
        resources.setPassword(passwordEncoder.encode("123456"));
        adminService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改用户")
    @PutMapping
    public ResponseEntity<Object> updateUser(@Validated(Admin.Update.class) @RequestBody Admin resources) throws Exception {
//        checkLevel(resources);
        adminService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    public ResponseEntity<Object> deleteUser(@RequestBody Set<Long> ids){
//        for (Long id : ids) {
//            Integer currentLevel =  Collections.min(roleService.findByUsersId(SecurityUtils.getCurrentUserId()).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
//            Integer optLevel =  Collections.min(roleService.findByUsersId(id).stream().map(RoleSmallDto::getLevel).collect(Collectors.toList()));
//            if (currentLevel > optLevel) {
//                throw new BadRequestException("角色权限不足，不能删除：" + userService.findById(id).getUsername());
//            }
//        }
        adminService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("重置密码")
    @DeleteMapping("resetPassword")
    public ResponseEntity<Object> resetPassword(@RequestBody List<Long> ids) throws Exception {
        for (Long id : ids) {
            Admin admin = adminService.resetPassword(id);
            admin.setPassword(passwordEncoder.encode("123456"));
            adminService.update(admin);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
