package com.cuckoom.blog.security;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;

import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

/**
 * 认证实现类
 * @author cuckooM
 */
public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    /** 用户业务逻辑层接口 */
    private final UserService userService;

    /**
     * 构造函数
     * @param userService 用户业务逻辑层接口
     */
    public UserDetailsAuthenticationProvider(@NonNull UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void additionalAuthenticationChecks(
        UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        TokenUser user = (TokenUser) userDetails;
        if (!StringUtils.hasText(user.getPassword()) || null == authentication.getCredentials()
            || !Objects.equals(user.getPassword(), authentication.getCredentials().toString())) {
            throw new BadCredentialsException("User name or password error.");
        }
    }

    @Override
    protected UserDetails retrieveUser(
        String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // 根据登录名查询用户
        UserDTO user = this.userService.findByUserName(username);
        if (null == user) {
            // 用户不存在
            throw new UsernameNotFoundException(String.format("User whose name is %s is not found.", username));
        }
        return new TokenUser(user.getId(), user.getUserName(), user.getDisplayName(), user.getPasswd(),
                new ArrayList<>());
    }

}
