package com.cuckoom.blog.manage.service;

import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.manage.dto.BlogDTO;
import com.cuckoom.blog.manage.entity.Blog;
import com.cuckoom.blog.manage.repository.BlogRepository;
import com.cuckoom.blog.manage.utils.BlogUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * 博客业务逻辑层
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    /** 博客数据访问层接口 */
    private final BlogRepository blogRepository;

    /** 多语言资源支持 */
    private final MessageService messageService;

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
        if (!userId.equals(original.getAuthorId())) {
            // 非作者不允许此操作！
            throw new PayloadException(messageService.getMessage("manage.blog.update.author.require"));
        }
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
        Optional<Blog> optional = blogRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new PayloadException(messageService.getMessage("http.404"));
    }
}
