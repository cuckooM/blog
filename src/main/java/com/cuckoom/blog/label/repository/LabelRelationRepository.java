package com.cuckoom.blog.label.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cuckoom.blog.common.entity.ModuleEnum;
import com.cuckoom.blog.label.entity.LabelRelation;

/**
 * 标签与数据的关联关系数据访问层接口
 * @author cuckooM
 */
public interface LabelRelationRepository  extends PagingAndSortingRepository<LabelRelation, Long>,
    JpaSpecificationExecutor<LabelRelation> {

    /**
     * 根据指定数据关联的标签关联关系数据列表
     * @param type 类型
     * @param entityId 数据 ID
     * @return 结果
     */
    List<LabelRelation> findByTypeAndEntityId(ModuleEnum type, Long entityId);
    /**
     * 根据指定数据关联的标签关联关系数据列表
     * @param type 类型
     * @param entityIds 数据 ID 集合
     * @return 结果
     */
    List<LabelRelation> findByTypeAndEntityIdIn(ModuleEnum type, Collection<Long> entityIds);

}
