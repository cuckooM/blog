package com.cuckoom.blog.blog.service;

import java.util.List;

import com.cuckoom.blog.blog.dto.BlogDTO;
import com.cuckoom.blog.blog.vo.BlogVO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 博客业务逻辑层接口
 * @author cuckooM
 */
public interface BlogService {

    /**
     * 分页查询数据
     * @param pageable 分页条件
     * @param authorId 作者 ID
     * @param labelIds 标签 ID 列表
     * @return 结果
     */
    @NonNull
    Page<BlogVO> page(@NonNull Pageable pageable, @Nullable Long authorId, @Nullable List<String> labelIds);

    /**
     * 根据 ID 查询数据
     * @param id ID
     * @return 结果。可能为 <code>null</code> .
     */
    @Nullable
    BlogVO findOne(@NonNull Long id);

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
