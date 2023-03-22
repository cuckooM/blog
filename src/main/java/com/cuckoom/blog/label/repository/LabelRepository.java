package com.cuckoom.blog.label.repository;

import com.cuckoom.blog.label.entity.Label;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 标签数据访问层接口
 * @author cuckooM
 */
public interface LabelRepository extends PagingAndSortingRepository<Label, Long>,
        JpaSpecificationExecutor<Label> {

    /**
     * 根据 ID 查询未删除的数据
     * @param id ID
     * @return 结果
     */
    Label findByIdAndDeletedFalse(Long id);
}
