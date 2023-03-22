package com.cuckoom.blog.label.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 标签 DTO 实体
 * @author cuckooM
 */
@Getter
@Setter
public class LabelDTO implements Serializable {
    private static final long serialVersionUID = 1736816011245583766L;

    /** ID */
    private Long id;

    /** 标签名称 */
    private String name;
}
