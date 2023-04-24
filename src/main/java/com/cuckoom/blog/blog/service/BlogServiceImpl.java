package com.cuckoom.blog.blog.service;

import com.cuckoom.blog.blog.vo.BlogVO;
import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.blog.dto.BlogDTO;
import com.cuckoom.blog.blog.entity.Blog;
import com.cuckoom.blog.blog.repository.BlogRepository;
import com.cuckoom.blog.blog.utils.BlogUtils;
import com.cuckoom.blog.label.entity.Label;
import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
    @Transactional(readOnly = true)
    public Page<BlogVO> page(@NonNull Pageable pageable, @Nullable String userName, @Nullable List<String> labelIds) {
        // 作者
        UserDTO user = null;
        if (null != userName) {
            user = userService.findByUserName(userName);
        }
        final Long authorId;
        if (null != user) {
            authorId = user.getId();
        } else {
            authorId = null;
        }
        // 查询条件
        Specification<Blog> spec = (Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            // 条件列表
            List<Predicate> predicates = new ArrayList<>();
            // 未删除条件
            predicates.add(cb.equal(root.get("deleted"), false));

            if (null != authorId) {
                // 作者条件
                predicates.add(cb.equal(root.get("authorId"), authorId));
            }
            if (null != labelIds && !labelIds.isEmpty()) {
                // 标签条件
                Join<Blog, Label> join = (Join<Blog, Label>) root.getJoins().stream()
                    .filter(item -> "labels".equals(item.getAttribute().getName())
                        && item.getJoinType() == JoinType.LEFT)
                    .findFirst()
                    .orElse(root.join("labels", JoinType.LEFT));
                predicates.add(join.get("id").in(labelIds));
            }
            return query.where(cb.and(predicates.toArray(new Predicate[0]))).getRestriction();
        };
        // 查询数据
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
            .map(item -> BlogUtils.toVO(item, users))
            .collect(Collectors.toList()),
            page.getPageable(), page.getTotalElements());
    }

    @Override
    @Nullable
    @Transactional(readOnly = true)
    public BlogVO findOne(@NonNull Long id) {
        Blog blog = blogRepository.findByIdAndDeletedFalse(id);
        if (null != blog) {
            // 查询作者
            List<UserDTO> users = new ArrayList<>();
            UserDTO user = userService.findById(blog.getAuthorId());
            if (null != user) {
                users.add(user);
            }
            return BlogUtils.toVO(blog, users);
        }
        return null;
    }

    @Override
    @NonNull
    @Transactional(rollbackFor = RuntimeException.class)
    public BlogDTO add(@NonNull BlogDTO dto, @NonNull Long userId) {
        // 查询用户
        UserDTO user = userService.findById(userId);
        // 保存数据
        Blog entity = blogRepository.save(BlogUtils.create(dto, userId));
        // 返回值
        return BlogUtils.toDTO(entity, user);

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
        original = blogRepository.save(original);
        // 返回值
        return BlogUtils.toDTO(original, user);
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
