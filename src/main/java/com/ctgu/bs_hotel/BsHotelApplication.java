package com.ctgu.bs_hotel;

import com.ctgu.bs_hotel.common.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.ctgu.bs_hotel.mapper")
public class BsHotelApplication {

    public static void main(String[] args) {

        SpringApplication.run(BsHotelApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
