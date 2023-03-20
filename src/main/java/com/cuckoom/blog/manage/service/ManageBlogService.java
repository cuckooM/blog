package com.cuckoom.blog.manage.service;

import com.cuckoom.blog.manage.dto.BlogDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 博客业务逻辑层接口
 * @author cuckooM
 */
public interface ManageBlogService {

    /**
     * 分页查询数据
     * @param pageable 分页条件
     * @param authorId 作者 ID
     * @return 结果
     */
    @NonNull
    Page<BlogDTO> page(@NonNull Pageable pageable, @Nullable Long authorId);

    /**
     * 增加
     * @param dto DTO 实体
     * @param userId 用户 ID
     * @return 结果
     */
    @NonNull
    BlogDTO add(@NonNull BlogDTO dto, @NonNull Long userId);

    /**
     * 编辑
     * @param id ID
     * @param dto DTO 实体
     * @param userId 用户 ID
     * @return 结果
     */
    @NonNull
    BlogDTO update(@NonNull Long id, @NonNull BlogDTO dto, @NonNull Long userId);

    /**
     * 删除
     * @param id ID
     * @param userId 用户 ID
     */
    void delete(@NonNull Long id, @NonNull Long userId);

}