package com.ctgu.bs_hotel.controller.admin;

import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.service.AdminService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

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
        return new ResponseEntity<>(adminService.queryAll(pageable), HttpStatus.OK);
    }

    @ApiOperation("新增用户")
    @PostMapping
    public ResponseEntity<Object> createUser(@Validated @RequestBody Admin resources){
        // 默认密码 123456
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
}
