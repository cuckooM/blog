package com.cuckoom.blog.blog.service;

import com.cuckoom.blog.blog.dto.BlogDiscussDTO;
import com.cuckoom.blog.blog.vo.BlogDiscussVO;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 博客讨论业务逻辑层接口
 * @author cuckooM
 */
public interface BlogDiscussService {

    /**
     * 查询指定博客的讨论数据
     * @param blogId 博客 ID
     * @return 结果
     */
    @NonNull
    List<BlogDiscussVO> findByBlogId(@NonNull Long blogId);

    /**
     * 增加
     * @param dto DTO 实体
     * @param userId 用户 ID
     * @return 结果
     */
    @NonNull
    BlogDiscussDTO add(@NonNull BlogDiscussDTO dto, @NonNull Long userId);

    /**
     * 删除
     * @param id ID
     * @param userId 用户 ID
     */
    void delete(@NonNull Long id, @NonNull Long userId);

}
