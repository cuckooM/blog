package com.cuckoom.blog.security;

import java.util.Collection;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 认证用户信息
 * @author cuckooM
 */
public class TokenUser extends User {

    /** 序列化 ID */
    private static final long serialVersionUID = 5041444006074000212L;

    /** 显示名称 */
    private String displayName;

    /**
     * 构造函数
     * @param username 登录名称
     * @param displayName 显示名称
     * @param password 密码
     * @param authorities 权限列表
     */
    public TokenUser(@NonNull String username, @NonNull String displayName, @NonNull String password,
                     @NonNull Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
