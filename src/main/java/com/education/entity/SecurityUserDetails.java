package com.education.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author dell
 */
public class SecurityUserDetails extends AdminInfo implements UserDetails {

    private static final long serialVersionUID = 1L;

    private List<PopedomInfo> popedomInfoList = new ArrayList<>();

    public SecurityUserDetails(AdminInfo user, List<PopedomInfo> popedomInfoList) {
        if (user != null) {
            this.setAdminNumber(user.getAdminNumber());
            /* this.setAdminName(user.getAdminName()); */
            this.setPassword(user.getPassword());
            this.popedomInfoList = popedomInfoList;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<PopedomInfo> popedomList = popedomInfoList;
        for (PopedomInfo popedom : popedomList) {
            authorities.add(new SimpleGrantedAuthority(popedom.getPopedomName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        /* return this.getAdminName(); */
        return String.valueOf(this.getAdminNumber());
    }

    /**
     * 判断是否过期，如果没有判断的话就要设为true，因为false代表这个账号过期了，账号都过期了，验证你干什么吗
     * @return bool
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 和前面差不多的道理，判断账号是否被锁定，锁定无法登录
     * @return bool
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
