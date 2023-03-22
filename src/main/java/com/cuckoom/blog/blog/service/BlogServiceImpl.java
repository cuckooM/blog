package com.cuckoom.blog.blog.service;

import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.blog.dto.BlogDTO;
import com.cuckoom.blog.blog.entity.Blog;
import com.cuckoom.blog.blog.repository.BlogRepository;
import com.cuckoom.blog.blog.utils.BlogUtils;
import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

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
import java.util.List;
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
public class BlogServiceImpl implements BlogService {

    /** 博客数据访问层接口 */
    private final BlogRepository blogRepository;

    /** 用户业务逻辑层接口 */
    private final UserService userService;

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
        // 查询用户
        List<UserDTO> users = userService.findByIds(
            page.getContent()
                .stream()
                .map(Blog::getAuthorId)
                .collect(Collectors.toSet()));
        // 返回数据
        return new PageImpl<>(page.getContent()
            .stream()
            .map(item -> BlogUtils.toDTO(item, users))
            .collect(Collectors.toList()),
            page.getPageable(), page.getTotalElements());
    }

    @Override
    @NonNull
    @Transactional(rollbackFor = RuntimeException.class)
    public BlogDTO add(@NonNull BlogDTO dto, @NonNull Long userId) {
        // 查询用户
        UserDTO user = userService.findById(userId);
        // 保存数据
        return BlogUtils.toDTO(
                blogRepository.save(
                        BlogUtils.create(dto, userId)), user);
    }

    @Override
    @NonNull
    @Transactional(rollbackFor = RuntimeException.class)
    public BlogDTO update(@NonNull Long id, @NonNull BlogDTO dto, @NonNull Long userId) {
        Blog original = findOrThrowException(id);
        if (!userId.equals(original.getAuthorId())) {
            // 非作者不允许此操作！
            throw new PayloadException(messageService.getMessage("manage.blog.update.author.require"));
        }
        // 查询用户
        UserDTO user = userService.findById(userId);
        // 保存数据
        BlogUtils.copyProperties(dto, original);
        original.setUpdateTime(new Date());
        return BlogUtils.toDTO(
                blogRepository.save(original), user);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
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
