package com.cuckoom.blog.label.service;

import com.cuckoom.blog.common.entity.ModuleEnum;
import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.label.entity.Label;
import com.cuckoom.blog.label.entity.LabelRelation;
import com.cuckoom.blog.label.repository.LabelRelationRepository;
import com.cuckoom.blog.label.repository.LabelRepository;
import com.cuckoom.blog.label.utils.LabelUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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

    /** 标签与数据的关联关系数据访问层接口 */
    private final LabelRelationRepository labelRelationRepository;

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

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void relate(@NonNull ModuleEnum type, @NonNull Long entityId, @Nullable Collection<Long> labelIds) {
        // 已有标签
        List<LabelRelation> list = labelRelationRepository.findByTypeAndEntityId(type, entityId);
        // 待删除标签 ID
        Set<Long> toDeleteIds = list.stream()
            .filter(item -> null == labelIds || !labelIds.contains(item.getLabelId()))
            .map(LabelRelation::getId)
            .collect(Collectors.toSet());
        if (!toDeleteIds.isEmpty()) {
            labelRelationRepository.deleteAllById(toDeleteIds);
        }
        // 待关联标签
        if (null != labelIds && !labelIds.isEmpty()) {
            Set<Long> existsIds = list.stream()
                .map(LabelRelation::getId)
                .collect(Collectors.toSet());
            Set<LabelRelation> toAdds = labelIds.stream()
                .filter(labelId -> !existsIds.contains(labelId))
                .map(labelId -> initLabelRelation(type, entityId, labelId))
                .collect(Collectors.toSet());
            if (!toAdds.isEmpty()) {
                labelRelationRepository.saveAll(toAdds);
            }
        }
    }

    @Override
    @NonNull
    public List<LabelRelation> listRelations(@NonNull ModuleEnum type, @NonNull Collection<Long> entityIds) {
        if (!entityIds.isEmpty()) {
            return labelRelationRepository.findByTypeAndEntityIdIn(type, entityIds);
        }
        return new ArrayList<>();
    }

    /**
     * 构建一个标签与数据的关联关系实体
     * @param type 类型
     * @param entityId 数据 ID
     * @param labelId 标签 ID
     * @return 结果
     */
    private LabelRelation initLabelRelation(@NonNull ModuleEnum type, @NonNull Long entityId, Long labelId) {
        LabelRelation entity = new LabelRelation();
        entity.setType(type);
        entity.setEntityId(entityId);
        entity.setLabelId(labelId);
        return entity;
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
