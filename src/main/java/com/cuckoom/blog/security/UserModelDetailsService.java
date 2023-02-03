package com.cuckoom.blog.security;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

   /** 用户业务逻辑层接口 */
   private final UserService userService;

   public UserModelDetailsService(@NonNull UserService userService) {
      this.userService = userService;
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String username) {
      log.debug("Authenticating user '{}'", username);

      // 根据登录名查询用户
      UserDTO user = this.userService.findByUserName(username);
      if (null == user) {
         // 用户不存在
         throw new UsernameNotFoundException(String.format("User whose name is %s is not found.", username));
      }
      return new TokenUser(user.getUserName(), user.getDisplayName(), user.getPasswd(), new ArrayList<>());

   }

}
