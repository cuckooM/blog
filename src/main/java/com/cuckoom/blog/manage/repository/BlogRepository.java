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

}
