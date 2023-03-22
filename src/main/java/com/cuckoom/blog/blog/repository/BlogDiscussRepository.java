package com.cuckoom.blog.blog.repository;

import com.cuckoom.blog.blog.entity.Blog;
import com.cuckoom.blog.blog.entity.BlogDiscuss;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 博客讨论数据访问层接口
 * @author cuckooM
 */
public interface BlogDiscussRepository extends PagingAndSortingRepository<BlogDiscuss, Long>,
    JpaSpecificationExecutor<BlogDiscuss> {

    /**
     * 根据博客 ID 查询数据
     * @param blogId 博客 ID
     * @return 结果
     */
    List<BlogDiscuss> findByBlogIdOrderByCreateTime(Long blogId);

    /**
     * 根据博客 ID 查询未删除的直接评论数量
     * @param blogId 博客 ID
     * @return 结果
     */
    Long countByBlogIdAndParentIdIsNullAndDeletedFalse(Long blogId);

}
