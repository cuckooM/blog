package com.cuckoom.blog.manage.label.service;

import com.cuckoom.blog.manage.label.dto.LabelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

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
}
