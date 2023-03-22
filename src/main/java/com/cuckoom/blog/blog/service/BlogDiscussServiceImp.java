package com.cuckoom.blog.blog.service;

import com.cuckoom.blog.blog.dto.BlogDiscussDTO;
import com.cuckoom.blog.blog.vo.BlogDiscussVO;

import java.util.List;

/**
 * 博客讨论业务逻辑层接口实现类
 * @author cuckooM
 */
public class BlogDiscussServiceImp implements BlogDiscussService {
    @Override
    public List<BlogDiscussVO> findByBlogId(Long blogId) {
        return null;
    }

    @Override
    public BlogDiscussDTO add(BlogDiscussDTO dto, Long userId) {
        return null;
    }

    @Override
    public void delete(Long id, Long userId) {

    }
}
