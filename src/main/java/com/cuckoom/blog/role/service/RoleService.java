package com.cuckoom.blog.role.service;

import java.util.List;

import com.cuckoom.blog.role.dto.RoleDTO;
import org.springframework.lang.NonNull;

import com.cuckoom.blog.role.entity.Role;

/**
 * 角色业务逻辑层接口
 * @author cuckooM
 */
public interface RoleService {

    /**
     * 根据用户 ID 查询列表
     * @param userId 用户 ID
     * @return 结果
     */
    @NonNull
    List<Role> findByUserId(@NonNull Long userId);

    /**
     * 增加
     * @param dto 实体
     * @return 结果
     */
    @NonNull
    RoleDTO add(@NonNull RoleDTO dto);

}
