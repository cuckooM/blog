package com.cuckoom.blog.role.service;

import com.cuckoom.blog.role.dto.PermissionDTO;
import com.cuckoom.blog.role.entity.Permission;
import com.cuckoom.blog.role.repository.PermissionRepository;
import com.cuckoom.blog.role.utils.RoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * 权限业务逻辑层接口实现类
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    /** 权限数据访问层接口 */
    private final PermissionRepository permissionRepository;

    @Override
    public List<PermissionDTO> list() {
        Iterable<Permission> list = permissionRepository.findAll();
        Map<Long, List<PermissionDTO>> permissions = StreamSupport.stream(list.spliterator(), false)
                .map(RoleUtils::convert)
                .sorted(Comparator.comparing(PermissionDTO::getId))
                .collect(Collectors.groupingBy(PermissionDTO::getParentId));
        // 最外层的列表
        List<PermissionDTO> result = permissions.get(null);
        if (null == result) {
            return new ArrayList<>();
        }
        initChildren(result, permissions);
        return result;
    }

    /**
     * 递归构造各层级子权限
     * @param list 权限列表
     * @param permissions 权限数据
     */
    private void initChildren(@Nullable List<PermissionDTO> list, @NonNull Map<Long, List<PermissionDTO>> permissions) {
        if (null != list && !list.isEmpty()) {
            for (PermissionDTO item : list) {
                List<PermissionDTO> children = permissions.get(item.getId());
                if (null != children) {
                    initChildren(children, permissions);
                    item.setChildren(children);
                }
            }
        }
    }

}
