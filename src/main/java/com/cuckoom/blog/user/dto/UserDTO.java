package com.cuckoom.blog.user.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

/**
 * 用户 DTO 实体
 * @author cuckooM
 */
public class UserDTO implements Serializable {

    /** 序列化 ID */
    private static final long serialVersionUID = 5988044502421274560L;

    /** 自增 ID */
    private Long id;

    /** 显示名称 */
    private String displayName;

    /** 登录名 */
    private String userName;

    /** 密码 */
    @Transient
    private String passwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
