package com.cuckoom.blog.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 博客讨论实体
 * @author cuckooM
 */
@Entity
@Table(name = "tbl_blog_discuss")
@Getter
@Setter
public class BlogDiscuss implements Serializable {

    private static final long serialVersionUID = 3261591480664234828L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 内容 */
    @Column(name = "content")
    private String content;

    /** 博客 ID */
    @Column(name = "blog_id")
    private Long blogId;

    /** 父讨论 ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 回复的讨论 ID */
    @Column(name = "reply_id")
    private Long replyId;

    /** 用户 ID */
    @Column(name = "user_id")
    private Long userId;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 是否已删除 */
    @Column(name = "deleted")
    private boolean deleted;

}
