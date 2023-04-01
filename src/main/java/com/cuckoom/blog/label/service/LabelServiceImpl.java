package com.cuckoom.blog.label.service;

import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.label.entity.Label;
import com.cuckoom.blog.label.repository.LabelRepository;
import com.cuckoom.blog.label.utils.LabelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * 标签业务逻辑
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    /** 标签数据访问接口 */
    private final LabelRepository labelRepository;

    /** 多语言资源支持 */
    private final MessageService messageService;

    @Override
    @NonNull
    public Page<LabelDTO> page(@NonNull Pageable pageable) {
        Page<Label> page = labelRepository.findAll(pageable);
        return new PageImpl<>(page.getContent()
                .stream()
                .map(LabelUtils::convert)
                .collect(Collectors.toList()),
                page.getPageable(), page.getTotalElements());
    }

    @Override
    @NonNull
    public LabelDTO add(@NonNull LabelDTO dto) {
        return LabelUtils.convert(labelRepository.save(LabelUtils.convert(dto)));
    }

    @Override
    @NonNull
    public LabelDTO update(@NonNull Long id, @NonNull LabelDTO dto) {
        Label original = findOrThrowException(id);
        BeanUtils.copyProperties(dto, original, "id");
        return LabelUtils.convert(labelRepository.save(original));
    }

    @Override
    public void delete(@NonNull Long id) {
        Label original = findOrThrowException(id);
        original.setDeleted(true);
        labelRepository.save(original);
    }

    /**
     * 根据 ID 查询数据，若不存在则抛出异常
     * @param id ID
     * @return 结果
     */
    @NonNull
    private Label findOrThrowException(@NonNull Long id) {
        Label entity = labelRepository.findByIdAndDeletedFalse(id);
        if (null == entity) {
            throw new PayloadException(messageService.getMessage("http.404"));
        }
        return entity;
    }
}
