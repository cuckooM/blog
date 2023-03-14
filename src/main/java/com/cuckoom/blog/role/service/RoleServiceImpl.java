package com.cuckoom.blog.role.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cuckoom.blog.role.entity.Role;
import com.cuckoom.blog.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

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
    public List<Role> findByUserId(Long userId) {
        return roleRepository.findByUsersId(userId);
    }
}
