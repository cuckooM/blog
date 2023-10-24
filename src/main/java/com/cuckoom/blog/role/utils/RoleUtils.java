package com.cuckoom.blog.role.utils;

import com.cuckoom.blog.role.dto.PermissionDTO;
import com.cuckoom.blog.role.dto.RoleDTO;
import com.cuckoom.blog.role.entity.Permission;
import com.cuckoom.blog.role.entity.Role;
import com.cuckoom.blog.user.utils.UserUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import java.util.stream.Collectors;

/**
 * 角色工具类
 * @author cuckooM
 */
public class RoleUtils {

    /** 防止实例化 */
    private RoleUtils() {
    }

    /**
     * 实体转换
     * @param dto {@link RoleDTO} 实体
     * @return {@link Role} 实体
     */
    @NonNull
    public static Role convert(@NonNull RoleDTO dto) {
        Role entity = new Role();
        BeanUtils.copyProperties(dto, entity, "users");
        if (null != dto.getUsers()) {
            entity.setUsers(dto.getUsers().stream().map(UserUtils::convert).collect(Collectors.toSet()));
        }
        return entity;
    }

    /**
     * 实体转换
     * @param entity {@link Role} 实体
     * @return {@link RoleDTO} 实体
     */
    @NonNull
    public static RoleDTO convert(@NonNull Role entity) {
        RoleDTO result = new RoleDTO();
        BeanUtils.copyProperties(entity, result, "users");
        if (null != entity.getUsers()) {
            result.setUsers(entity.getUsers().stream().map(UserUtils::convert).collect(Collectors.toSet()));
        }
        return result;
    }

    /**
     * 实体转换
     * @param entity {@link Permission} 实体
     * @return {@link PermissionDTO} 实体
     */
    @NonNull
    public static PermissionDTO convert(@NonNull Permission entity) {
        PermissionDTO dto = new PermissionDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
