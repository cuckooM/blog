package com.cuckoom.blog.role.service;

import java.util.List;

import com.cuckoom.blog.role.dto.RoleDTO;
import com.cuckoom.blog.user.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import com.cuckoom.blog.role.entity.Role;

/**
 * 角色业务逻辑层接口
 * @author cuckooM
 */
public interface RoleService {


    /**
     * 分页查询
     * @param pageable 分页条件
     * @return 结果
     */
    @NonNull
    Page<RoleDTO> page(@NonNull Pageable pageable);

    /**
     * 增加
     * @param dto 实体
     * @return 结果
     */
    @NonNull
    RoleDTO add(@NonNull RoleDTO dto);

    /**
     * 编辑
     * @param id ID
     * @param dto 实体
     * @return 结果
     */
    @NonNull
    RoleDTO update(@NonNull Long id, @NonNull RoleDTO dto);

    /**
     * 删除
     * @param id ID
     */
    void delete(@NonNull Long id);

    /**
     * 根据用户 ID 查询列表
     * @param userId 用户 ID
     * @return 结果
     */
    @NonNull
    List<Role> findByUserId(@NonNull Long userId);

}
