package com.cuckoom.blog.manage.service;

import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.manage.dto.BlogDTO;
import com.cuckoom.blog.manage.entity.Blog;
import com.cuckoom.blog.manage.repository.BlogRepository;
import com.cuckoom.blog.manage.utils.BlogUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

/**
 * 博客业务逻辑层
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    /** 博客数据访问层接口 */
    private final BlogRepository blogRepository;

    @Override
    @NonNull
    public BlogDTO add(@NonNull BlogDTO dto, @NonNull Long userId) {
        return BlogUtils.toDTO(
                blogRepository.save(
                        BlogUtils.create(dto, userId)));
    }

    @Override
    @NonNull
    public BlogDTO update(@NonNull Long id, @NonNull BlogDTO dto, @NonNull Long userId) {
        Blog original = findOrThrowException(id);
        BlogUtils.copyProperties(dto, original);
        original.setUpdateTime(new Date());
        return BlogUtils.toDTO(
                blogRepository.save(original));
    }

    /**
     * 根据 ID 查询数据，若不存在则抛出异常
     * @param id ID
     * @return 结果
     */
    @NonNull
    private Blog findOrThrowException(@NonNull Long id) {
        try {
            return blogRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new PayloadException(e);
        }
    }
}
