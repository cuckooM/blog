package com.cuckoom.blog.label.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 标签
 * @author cuckooM
 */
@Entity
@Table(name = "tbl_label")
@Getter
@Setter
public class Label implements Serializable {
    private static final long serialVersionUID = -5188110654308437610L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 标签名称 */
    @Column(name = "name")
    private String name;

    /** 是否已删除 */
    @Column(name = "deleted")
    private boolean deleted;
}
