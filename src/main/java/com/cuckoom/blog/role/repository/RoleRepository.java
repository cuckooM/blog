package com.cuckoom.blog.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuckoom.blog.role.entity.Role;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色数据访问层接口
 * @author cuckooM
 */
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>,
    JpaSpecificationExecutor<Role> {

    /**
     * 根据用户 ID 查询列表
     * @param userId 用户 ID
     * @return 列表
     */
    List<Role> findByUsersIdAndDeletedFalse(@NonNull Long userId);

    /**
     * 逻辑删除
     * @param id ID
     */
    @Transactional
    @Modifying
    @Query("update Role set deleted = true where id = ?1")
    void delete(@NonNull Long id);

}
