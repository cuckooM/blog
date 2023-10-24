package com.cuckoom.blog.role.service;

import com.cuckoom.blog.role.dto.PermissionDTO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 权限业务逻辑层接口
 * @author cuckooM
 */
public interface PermissionService {

    /**
     * 查询列表数据。按层级组装数据。
     * @return 结果
     */
    @NonNull
    List<PermissionDTO> list();

}
