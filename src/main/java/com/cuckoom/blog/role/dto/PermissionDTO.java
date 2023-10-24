package com.cuckoom.blog.role.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限 DTO 实体
 * @author cuckooM
 */
@Getter
@Setter
public class PermissionDTO implements Serializable {

    private static final long serialVersionUID = -5811967665073909598L;

    /** ID */
    private Long id;

    /** 名称 */
    private String name;

    /** 权限标志 */
    private String key;

    /** 父级 ID */
    private Long parentId;

    /** 子级权限列表 */
    private List<PermissionDTO> children = new ArrayList<>();

}
