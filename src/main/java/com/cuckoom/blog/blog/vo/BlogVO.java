package com.cuckoom.blog.blog.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.user.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

/**
 * 博客 VO 实体
 * @author cuckooM
 */
@Getter
@Setter
public class BlogVO implements Serializable {
    private static final long serialVersionUID = 3110904343856107185L;

    /** ID */
    private Long id;

    /** 标题 */
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

    /** 标签 */
    private List<LabelDTO> labels;
}
