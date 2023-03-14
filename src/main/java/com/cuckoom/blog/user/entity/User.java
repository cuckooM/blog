package com.cuckoom.blog.user.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体
 * @author cuckooM
 */
@Entity
@Table(name = "tbl_user")
@Getter
@Setter
public class User implements Serializable {

    /** 序列化 ID */
    private static final long serialVersionUID = 259340450257656027L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 显示名称 */
    @Column(name = "display_name")
    private String displayName;

    /** 登录名 */
    @Column(name = "user_name")
    private String userName;

    /** 密码 */
    private String passwd;

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 是否已删除 */
    private boolean deleted;

}
