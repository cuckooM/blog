package com.cuckoom.blog.label.dto;

import java.io.Serializable;

import com.cuckoom.blog.common.entity.ModuleEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 标签与数据的关联关系
 * @author cuckooM
 */
@Getter
@Setter
public class LabelRelationDTO implements Serializable {
    private static final long serialVersionUID = 2019549956145809422L;

    /** ID */
    private Long id;

    /** 标签 ID */
    private Long labelId;

    /** 数据 ID */
    private Long entityId;

    /** 类型 */
    private ModuleEnum type;
}
