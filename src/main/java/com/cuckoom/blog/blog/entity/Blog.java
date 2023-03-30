package com.cuckoom.blog.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cuckoom.blog.label.entity.Label;

/**
 * 博客实体
 * @author cuckooM
 */
@Entity
@Table(name = "tbl_blog")
@Getter
@Setter
public class Blog implements Serializable {
    private static final long serialVersionUID = -6314734154988222963L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 标题 */
    @Column(name = "title")
    private String title;

    /** 内容 */
    @Column(name = "content")
    private String content;

    /** 作者 ID */
    @Column(name = "author_id")
    private Long authorId;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 修改时间 */
    @Column(name = "update_time")
    private Date updateTime;

    /** 标签 */
    @ManyToMany(targetEntity = Label.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_label_relation",
        joinColumns = @JoinColumn(name = "entity_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    private List<Label> labels;

    /** 是否已删除 */
    @Column(name = "deleted")
    private boolean deleted;

}
