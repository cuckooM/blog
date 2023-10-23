package com.cuckoom.blog.user.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 * 用户 DTO 实体
 * @author cuckooM
 */
@Getter
@Setter
public class UserDTO implements Serializable {

    /** 序列化 ID */
    private static final long serialVersionUID = 5988044502421274560L;

    /** 自增 ID */
    private Long id;

    /** 显示名称 */
    @Length(max = 128)
    private String displayName;

    /** 登录名 */
    @Length(max = 64)
    private String userName;

    /** 密码 */
    @JsonIgnore
    @Length(max = 128)
    private String passwd;
}
