package com.cuckoom.blog.user.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.entity.User;

/**
 * 用户工具类
 * @author cuckooM
 */
public class UserUtils {

    /** 防止实例化 */
    private UserUtils(){
    }

    /**
     * 将 {@link User} 实体转化为 {@link UserDTO} 实体
     * @param entity {@link User} 实体
     * @return {@link UserDTO} 实体
     */
    @NonNull
    public static UserDTO convert(@NonNull User entity) {
        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    /**
     * 将 {@link UserDTO} 实体转化为 {@link User} 实体
     * @param dto {@link UserDTO} 实体
     * @return {@link User} 实体
     */
    @NonNull
    public static User convert(@NonNull UserDTO dto) {
        User result = new User();
        BeanUtils.copyProperties(dto, result, "passwd");
        return result;
    }

}
