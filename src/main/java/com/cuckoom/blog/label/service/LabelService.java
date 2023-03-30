package com.cuckoom.blog.label.service;

import java.util.Collection;
import java.util.List;

import com.cuckoom.blog.common.entity.ModuleEnum;
import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.label.entity.LabelRelation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 标签业务逻辑接口
 * @author cuckooM
 */
public interface LabelService {

    /**
     * 分页查询数据
     * @param pageable 分页条件
     * @return 结果
     */
    @NonNull
    Page<LabelDTO> page(@NonNull Pageable pageable);

    /**
     * 增加
     * @param dto DTO 实体
     * @return 结果
     */
    @NonNull
    LabelDTO add(@NonNull LabelDTO dto);

    /**
     * 编辑
     * @param id ID
     * @param dto DTO 实体
     * @return 结果
     */
    @NonNull
    LabelDTO update(@NonNull Long id, @NonNull LabelDTO dto);

    /**
     * 删除
     * @param id ID
     */
    void delete(@NonNull Long id);

    /**
     * 全量打标签
     * @param type 类型
     * @param entityId 数据 ID
     * @param labelIds 标签 ID 集合
     */
    void relate(@NonNull ModuleEnum type, @NonNull Long entityId, @Nullable Collection<Long> labelIds);

    /**
     * 查询关联的标签数据列表
     * @param type 类型
     * @param entityIds 数据 ID 集合
     * @return 结果
     */
    @NonNull
    List<LabelRelation> listRelations(@NonNull ModuleEnum type, @NonNull Collection<Long> entityIds);
}
