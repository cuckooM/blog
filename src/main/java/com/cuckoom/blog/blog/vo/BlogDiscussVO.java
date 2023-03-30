package com.cuckoom.blog.blog.vo;

import com.cuckoom.blog.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客讨论 DTO 实体
 * @author cuckooM
 */
@Setter
@Getter
public class BlogDiscussVO implements Serializable {

    private static final long serialVersionUID = 3323224438375962864L;

    /** ID */
    private Long id;

    /** 内容 */
    private String content;

    /** 博客 ID */
    private Long blogId;

    /** 回复的用户 */
    private UserDTO replyUser;

    /** 用户 ID */
    private Long userId;

    /** 用户 */
    private UserDTO user;

    /** 创建时间 */
    private Date createTime;

    /** 是否已删除 */
    private Boolean deleted;

    /** 子讨论列表 */
    private List<BlogDiscussVO> children = new ArrayList<>();

}
