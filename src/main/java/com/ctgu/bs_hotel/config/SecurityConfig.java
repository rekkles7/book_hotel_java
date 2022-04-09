package com.ctgu.bs_hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * ClassName SecurityConfig
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:22 下午
 */
@Configuration
@EnableWebSecurity
//开启了这个，才能在方法上使用权限控制注解
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/code").permitAll()
                .antMatchers("/user/hotel/*").permitAll()
                .antMatchers("/user/room/*").permitAll()
                .antMatchers("/user/order/*").permitAll()
                .antMatchers("/user/wx/*").permitAll()
                .antMatchers("/user/*").permitAll()
                // 放行OPTIONS请求，放行了才能把status放到data里面
                .antMatchers(HttpMethod.OPTIONS, "/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
