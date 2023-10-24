package com.cuckoom.blog.role.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.role.dto.RoleDTO;
import com.cuckoom.blog.role.utils.RoleUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.cuckoom.blog.role.entity.Role;
import com.cuckoom.blog.role.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色业务逻辑层接口实现类
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    /** 角色数据访问层接口 */
    private final RoleRepository roleRepository;

    /** 多语言资源支持接口 */
    private final MessageService messageService;

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public Page<RoleDTO> page(@NonNull Pageable pageable) {
        Page<Role> page = roleRepository.findAll(pageable);
        return new PageImpl<>(page.getContent().stream().map(RoleUtils::convert).collect(Collectors.toList()),
                pageable, page.getTotalElements());
    }

    @NonNull
    @Override
    @Transactional
    public RoleDTO add(@NonNull RoleDTO dto) {
        Role entity = RoleUtils.convert(dto);
        entity.setId(null);
        entity.setCreateTime(new Date());
        entity = roleRepository.save(entity);
        return RoleUtils.convert(entity);
    }

    @NonNull
    @Override
    @Transactional
    public RoleDTO update(@NonNull Long id, @NonNull RoleDTO dto) {
        Role entity = roleRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new PayloadException(messageService.getMessage("error.not.exist"));
        }
        entity = RoleUtils.convert(dto);
        entity.setId(id);
        entity = roleRepository.save(entity);
        return RoleUtils.convert(entity);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        Role entity = roleRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new PayloadException(messageService.getMessage("error.not.exist"));
        }
        roleRepository.delete(id);
    }

    @Override
    @NonNull
    public List<Role> findByUserId(@NonNull Long userId) {
        return roleRepository.findByUsersIdAndDeletedFalse(userId);
    }

}
