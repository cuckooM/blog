package com.cuckoom.blog.user.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.cuckoom.blog.user.entity.User;

/**
 * 用户数据访问层接口
 * @author cuckooM
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
    JpaSpecificationExecutor<User> {

    /**
     * 根据登录名查询未删除用户
     * @param userName 登录名
     * @return 结果
     */
    @Nullable
    User findTopByUserNameAndDeletedFalse(@NonNull String userName);

}
