package com.cuckoom.blog.role.repository;

import com.cuckoom.blog.role.entity.Permission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 权限数据访问层接口
 * @author cuckooM
 */
public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long>,
        JpaSpecificationExecutor<Permission> {

}
