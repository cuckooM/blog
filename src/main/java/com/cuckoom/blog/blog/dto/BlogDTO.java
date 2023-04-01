package com.cuckoom.blog.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.user.dto.UserDTO;

/**
 * 博客 DTO 实体
 * @author cuckooM
 */
@Setter
@Getter
public class BlogDTO implements Serializable {
    private static final long serialVersionUID = 5853078103938166801L;

    /** ID */
    private Long id;

    /** 标题 */
    @Length(max = 128)
    private String title;

    /** 内容 */
    private String content;

    /** 作者 ID */
    private Long authorId;

    /** 作者 */
    private UserDTO author;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 标签集合 */
    private List<LabelDTO> labels;

}
