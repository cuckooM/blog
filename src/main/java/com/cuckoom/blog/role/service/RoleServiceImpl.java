package com.cuckoom.blog.role.service;

import java.util.List;

import com.cuckoom.blog.role.dto.RoleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.cuckoom.blog.role.entity.Role;
import com.cuckoom.blog.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

/**
 * 角色业务逻辑层接口实现类
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    /** 角色数据访问层接口 */
    private final RoleRepository roleRepository;

    @Override
    @NonNull
    public List<Role> findByUserId(@NonNull Long userId) {
        return roleRepository.findByUsersId(userId);
    }


    @NonNull
    @Override
    @Transactional
    public RoleDTO add(@NonNull RoleDTO dto) {
        Role entity = new Role();
        BeanUtils.copyProperties(dto, entity, "id", "users");
        if (null != dto.getUsers()) {

        }
        entity = roleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
}
