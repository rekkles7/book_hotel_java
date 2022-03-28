package com.ctgu.bs_hotel.controller.admin;

import com.ctgu.bs_hotel.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName AdminRoleController
 * Description
 * Create by luochuang
 * Date 2022/3/27 9:44 上午
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
public class AdminRoleController {

    private final RoleService roleService;

    @ApiOperation("返回全部的角色")
    @GetMapping(value = "/all")
    public ResponseEntity<Object> queryAllRole(){
        return new ResponseEntity<>(roleService.queryAll(), HttpStatus.OK);
    }

}
