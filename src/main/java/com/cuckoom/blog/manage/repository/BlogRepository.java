package com.cuckoom.blog.manage.repository;

import com.cuckoom.blog.manage.entity.Blog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 博客数据访问层接口
 * @author cuckooM
 */
public interface BlogRepository extends PagingAndSortingRepository<Blog, Long>,
    JpaSpecificationExecutor<Blog> {

    /**
     * 根据 ID 查询未删除的数据
     * @param id ID
     * @return 结果
     */
    Blog findByIdAndDeletedFalse(Long id);

}
