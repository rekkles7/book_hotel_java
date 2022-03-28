package com.ctgu.bs_hotel.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * ClassName AuthAdminDto
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:51 下午
 */
@Getter
@Setter
public class AuthAdminDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
