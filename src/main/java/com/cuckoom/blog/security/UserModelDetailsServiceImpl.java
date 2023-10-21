package com.cuckoom.blog.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cuckoom.blog.role.entity.Permission;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cuckoom.blog.role.entity.Role;
import com.cuckoom.blog.role.service.RoleService;
import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

/**
 * Authenticate a user from the database.
 * @author cuckooM
 */
@Component("userDetailsService")
@RequiredArgsConstructor
public class UserModelDetailsServiceImpl implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserModelDetailsServiceImpl.class);

   /** 用户业务逻辑层接口 */
   private final UserService userService;

   /** 角色业务逻辑层接口 */
   private final RoleService roleService;

   @Override
   public UserDetails loadUserByUsername(final String username) {
      log.debug("Authenticating user '{}'", username);

      // 根据登录名查询用户
      UserDTO user = this.userService.findByUserName(username);
      if (null == user) {
         // 用户不存在
         throw new UsernameNotFoundException(String.format("User whose name is %s is not found.", username));
      }

      // 查询用户角色获得权限列表
      List<Role> roles = roleService.findByUserId(user.getId());
      List<GrantedAuthority> authorities = new ArrayList<>();
      if (!roles.isEmpty()) {
         for (Role role : roles) {
            Set<Permission> permissions = role.getPermissions();
            if (null != permissions && !permissions.isEmpty()) {
               for (Permission permission : permissions) {
                  authorities.add(new SimpleGrantedAuthority(permission.getKey()));
               }
            }
         }
      }

      return new TokenUser(user.getId(), user.getUserName(), user.getDisplayName(), user.getPasswd(),
          authorities);

   }

}
