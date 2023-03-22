package com.cuckoom.blog.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客讨论 DTO 实体
 * @author cuckooM
 */
@Setter
@Getter
public class BlogDiscussDTO implements Serializable {
    private static final long serialVersionUID = -5877360149587100107L;

    /** ID */
    private Long id;

    /** 内容 */
    private String content;

    /** 博客 ID */
    private Long blogId;

    /** 父讨论 ID */
    private Long parentId;

    /** 回复的讨论 ID */
    private Long replyId;

    /** 用户 ID */
    private Long userId;

    /** 创建时间 */
    private Date createTime;

    /** 是否已删除 */
    private boolean deleted;

}
