package com.ctgu.bs_hotel.controller.admin;


import com.ctgu.bs_hotel.common.SecurityUtils;
import com.ctgu.bs_hotel.service.MenuService;
import com.ctgu.bs_hotel.service.dto.MenuDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menus")
public class AdminMenuController {
//    @GetMapping(value = "/build")
//    @ApiOperation("获取前端所需菜单")
//    public ResponseEntity<Object> buildMenus() {
//        //这里返回的简单信息只会在控制台输出
//        return new ResponseEntity<>("菜单接口有待完善！", HttpStatus.OK);
//    }

    private final MenuService menuService;

    @GetMapping(value = "/build")
    @ApiOperation("获取前端所需菜单")
    public ResponseEntity<Object> buildMenus(){
        //获取当前登录用户所能看到的菜单列表，非树形列表
        List<MenuDto> menuDtoList = menuService.findByUser(SecurityUtils.getCurrentUserId());
        //把获取回来的菜单列表转化成树形菜单列表
        List<MenuDto> menuDtos = menuService.buildTree(menuDtoList);
        //把DTO转化成VO
        return new ResponseEntity<>(menuService.buildMenus(menuDtos),HttpStatus.OK);
    }
}
