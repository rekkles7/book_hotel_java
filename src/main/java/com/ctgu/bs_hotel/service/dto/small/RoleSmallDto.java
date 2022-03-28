package com.ctgu.bs_hotel.service.dto.small;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName RoleSmallDto
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:50 下午
 */
@Data
public class RoleSmallDto implements Serializable {

    private Long id;

    private String name;

    private Integer level;

    private String dataScope;
}
