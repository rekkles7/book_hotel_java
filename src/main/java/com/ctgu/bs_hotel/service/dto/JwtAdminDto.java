package com.ctgu.bs_hotel.service.dto;

/**
 * ClassName JwtAdminDto
 * Description
 * Create by luochuang
 * Date 2022/3/25 4:35 下午
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 认证的主体
 * 身份证认证系统 -> 主体 -> 身份证  （身份证号码，姓名，户籍所在地，头像）
 * 刷脸过门禁的认证系统       人脸
 */
@Getter
@AllArgsConstructor
public class JwtAdminDto implements UserDetails {

    private final AdminDto admin;

    private final List<Long> dataScopes;

    @JsonIgnore
    private final List<GrantedAuthority> authorities;

    public Set<String> getRoles() {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return admin.getUsername();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return admin.getEnabled();
    }
}
