package com.cuckoom.blog.user.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.cuckoom.blog.user.dto.UserDTO;

/**
 * 用户业务逻辑层接口
 * @author cuckooM
 */
public interface UserService {

    /**
     * 增加用户
     * @param dto 用户 DTO 实体
     * @return 结果
     */
    @NonNull
    UserDTO add(@NonNull UserDTO dto);

    /**
     * 编辑用户
     * @param id 用户 ID
     * @param dto 用户 DTO 实体
     * @return 结果
     */
    @NonNull
    UserDTO update(@NonNull Long id, @NonNull UserDTO dto);

    /**
     * 删除用户
     * @param id 用户 ID
     */
    void delete(@NonNull Long id);

    /**
     * 根据登录名查询用户
     * @param userName 登录名
     * @return 结果
     */
    @Nullable
    UserDTO findByUserName(@NonNull String userName);

    /**
     * 根据 ID 查询用户
     * @param userId ID
     * @return 结果
     */
    @Nullable
    UserDTO findById(@NonNull Long userId);

    /**
     * 根据 ID 集合查询用户
     * @param userIds ID 集合
     * @return 结果
     */
    @NonNull
    List<UserDTO> findByIds(@NonNull Collection<Long> userIds);

    /**
     * 分页查询
     * @param pageable 分页条件
     * @return 结果
     */
    @NonNull
    Page<UserDTO> page(@NonNull Pageable pageable);

}
