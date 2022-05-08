package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.CosUtils;
import com.ctgu.bs_hotel.common.GlobalResult;
import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.entity.Room;
import com.ctgu.bs_hotel.service.AdminService;
import com.ctgu.bs_hotel.service.HotelService;
import com.ctgu.bs_hotel.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.Query;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * ClassName AdminRoomController
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:23 下午
 */
@RestController
@RequestMapping("/admin/room")
public class AdminRoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private HotelService hotelService;

    @PostMapping("/upload")
    public String uploadImg(@RequestParam("files") MultipartFile file) throws IllegalStateException, IOException {
        CosUtils client = new CosUtils();
        String url = client.uploadFile(file);
        System.out.println(file.getOriginalFilename() + "图片已传入!!");
        return url;
    }

    @ApiOperation("新增前查询酒店ID")
    @GetMapping("/selectHotelId")
    public Long selectHotelId(@RequestParam("adminId") int adminId){
        Admin admin = adminService.selectHotelIdByAdminId(adminId);
        return admin.getHotelId();
    }

    @ApiOperation("查询所有房型")
    @GetMapping("/selectAllRoom")
    public GlobalResult selectRoom(@RequestParam("adminId") int adminId){
        Admin admin = adminService.selectHotelIdByAdminId(adminId);
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("hotel_id",admin.getHotelId());
        List<Room> roomList = roomService.list(wrapper);
        return GlobalResult.ok(roomList);
    }

    @ApiOperation("新增房型")
    @PostMapping
    public ResponseEntity<Object> createRoom(@Validated @RequestBody Room resources){
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("hotel_id",resources.getHotelId());
        List<Room> roomList = roomService.list(roomQueryWrapper);
        if (roomList.size() == 0){
            hotelService.setMinPrice(resources.getHotelId(),resources.getRoomPrice());
        }else{
            int minPrice = roomService.findMinPrice(resources.getHotelId());
            if (minPrice > resources.getRoomPrice()){
                minPrice = resources.getRoomPrice();
                hotelService.setMinPrice(resources.getHotelId(),minPrice);
            }
        }
        roomService.save(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改房型")
    @PutMapping
    public ResponseEntity<Object> updateRoom(@RequestBody Room resources) throws Exception {
        int minPrice = roomService.findMinPrice(resources.getHotelId());
        if (minPrice > resources.getRoomPrice()){
            minPrice = resources.getRoomPrice();
            hotelService.setMinPrice(resources.getHotelId(),minPrice);
        }
        QueryWrapper<Room> wrapper = new QueryWrapper<>();
        wrapper.eq("room_id",resources.getRoomId());
        roomService.update(resources, wrapper);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除房型")
    @DeleteMapping
    public ResponseEntity<Object> deleteRoom(@RequestBody Set<Long> ids){
        Room room = null;
        for (Long id : ids) {
            room = roomService.getById(id);
            break;
        }
        roomService.removeByIds(ids);
        int minPrice = roomService.findMinPrice(room.getHotelId());
        hotelService.setMinPrice(room.getHotelId(),minPrice);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
