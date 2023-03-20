package com.cuckoom.blog.manage.service;

import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.manage.dto.BlogDTO;
import com.cuckoom.blog.manage.entity.Blog;
import com.cuckoom.blog.manage.repository.BlogRepository;
import com.cuckoom.blog.manage.utils.BlogUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 博客业务逻辑层
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class ManageBlogServiceImpl implements ManageBlogService {

    /** 博客数据访问层接口 */
    private final BlogRepository blogRepository;

    /** 多语言资源支持 */
    private final MessageService messageService;

    @Override
    @NonNull
    public Page<BlogDTO> page(@NonNull Pageable pageable, @Nullable Long authorId) {
        // 查询条件
        Specification<Blog> spec = (Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            // 未删除条件
            Predicate predicate = cb.equal(root.get("deleted"), false);
            if (null != authorId) {
                // 作者条件
                predicate = cb.and(predicate, cb.equal(root.get("authorId"), authorId));
            }
            return query.where(predicate).getRestriction();
        };
        Page<Blog> page = blogRepository.findAll(spec, pageable);
        return new PageImpl<>(page.getContent()
            .stream()
            .map(BlogUtils::toDTO)
            .collect(Collectors.toList()),
            page.getPageable(), page.getTotalElements());
    }

    @Override
    @NonNull
    @Transactional
    public BlogDTO add(@NonNull BlogDTO dto, @NonNull Long userId) {
        return BlogUtils.toDTO(
                blogRepository.save(
                        BlogUtils.create(dto, userId)));
    }

    @Override
    @NonNull
    @Transactional
    public BlogDTO update(@NonNull Long id, @NonNull BlogDTO dto, @NonNull Long userId) {
        Blog original = findOrThrowException(id);
        if (!userId.equals(original.getAuthorId())) {
            // 非作者不允许此操作！
            throw new PayloadException(messageService.getMessage("manage.blog.update.author.require"));
        }
        BlogUtils.copyProperties(dto, original);
        original.setUpdateTime(new Date());
        return BlogUtils.toDTO(
                blogRepository.save(original));
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id, @NonNull Long userId) {
        Blog original = findOrThrowException(id);
        if (!userId.equals(original.getAuthorId())) {
            // 非作者不允许此操作！
            throw new PayloadException(messageService.getMessage("manage.blog.update.author.require"));
        }
        original.setDeleted(true);
        blogRepository.save(original);
    }

    /**
     * 根据 ID 查询数据，若不存在则抛出异常
     * @param id ID
     * @return 结果
     */
    @NonNull
    private Blog findOrThrowException(@NonNull Long id) {
        Blog blog = blogRepository.findByIdAndDeletedFalse(id);
        if (null == blog) {
            throw new PayloadException(messageService.getMessage("http.404"));
        }
        return blog;
    }

}
