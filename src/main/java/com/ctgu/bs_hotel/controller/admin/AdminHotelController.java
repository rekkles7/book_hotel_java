package com.ctgu.bs_hotel.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ctgu.bs_hotel.common.CosUtils;
import com.ctgu.bs_hotel.entity.Admin;
import com.ctgu.bs_hotel.entity.Hotel;
import com.ctgu.bs_hotel.mapper.HotelMapper;
import com.ctgu.bs_hotel.service.HotelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Set;

/**
 * ClassName AdminHotelController
 * Description
 * Create by luochuang
 * Date 2022/3/2 2:23 下午
 */
@RestController
@RequestMapping("/admin/hotel")
public class AdminHotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/upload")
    public String uploadImg(@RequestParam("files") MultipartFile file) throws IllegalStateException, IOException {
        System.out.println(1111);
        CosUtils client = new CosUtils();
        String url = client.uploadFile(file);
        System.out.println(file.getOriginalFilename() + "图片已传入!!");
        return url;
    }

    @ApiOperation("新增酒店")
    @PostMapping
    public ResponseEntity<Object> createHotel(@Validated @RequestBody Hotel resources){
        System.out.println(resources.toString());
        hotelService.save(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("修改酒店")
    @PutMapping
    public ResponseEntity<Object> updateHotel(@RequestBody Hotel resources) throws Exception {
//        checkLevel(resources);
        QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
        wrapper.eq("hotel_id",resources.getHotelId());
        hotelService.update(resources, wrapper);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation("删除酒店")
    @DeleteMapping
    public ResponseEntity<Object> deleteHotel(@RequestBody Set<Long> ids){
        for (Long id : ids) {
            System.out.println(id);
        }
        hotelService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
