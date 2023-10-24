package com.cuckoom.blog.role.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限实体
 * @author cuckooM
 */
@Entity
@Table(name = "tbl_permission")
@Getter
@Setter
public class Permission implements Serializable  {
    private static final long serialVersionUID = -6745330502230645683L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 名称 */
    private String name;

    /** 权限标志 */
    private String key;

    /** 父级权限 */
    @Column(name = "parent_id")
    private Long parentId;

}
