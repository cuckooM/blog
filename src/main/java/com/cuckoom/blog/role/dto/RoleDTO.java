package com.cuckoom.blog.role.dto;

import com.cuckoom.blog.role.entity.Permission;
import com.cuckoom.blog.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色 DTO 实体
 * @author cuckooM
 */
@Getter
@Setter
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 7494119337107501512L;

    /** ID */
    private Long id;

    /** 名称 */
    private String name;

    /** 关联的人员集合 */
    private Set<UserDTO> users = new HashSet<>();

    /** 权限列表 */
    private Set<Permission> permissions = new HashSet<>();
}
