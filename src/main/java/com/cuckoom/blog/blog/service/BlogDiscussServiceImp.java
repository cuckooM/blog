package com.cuckoom.blog.blog.service;

import com.cuckoom.blog.blog.dto.BlogDiscussDTO;
import com.cuckoom.blog.blog.entity.BlogDiscuss;
import com.cuckoom.blog.blog.repository.BlogDiscussRepository;
import com.cuckoom.blog.blog.vo.BlogDiscussVO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 博客讨论业务逻辑层接口实现类
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class BlogDiscussServiceImp implements BlogDiscussService {

    /** 博客讨论数据访问层接口 */
    private final BlogDiscussRepository blogDiscussRepository;

    @Override
    public List<BlogDiscussVO> findByBlogId(@NonNull Long blogId) {
        List<BlogDiscussVO> result = new ArrayList<>();
        List<BlogDiscuss> list = blogDiscussRepository.findByBlogIdOrderByCreateTime(blogId);
        for (BlogDiscuss item : list) {
            if (null == item.getParentId()) {

            }
        }
        return result;
    }

    @Override
    public BlogDiscussDTO add(BlogDiscussDTO dto, Long userId) {
        return null;
    }

    @Override
    public void delete(Long id, Long userId) {

    }
}
