package com.cuckoom.blog.manage.service;

import com.cuckoom.blog.manage.dto.BlogDTO;
import org.springframework.lang.NonNull;

/**
 * 博客业务逻辑层接口
 * @author cuckooM
 */
public interface BlogService {

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

}
