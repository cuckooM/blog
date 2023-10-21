package com.cuckoom.blog.role.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.cuckoom.blog.user.entity.User;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色实体
 * @author cuckooM
 */
@Entity
@Table(name = "tbl_role")
@Getter
@Setter
public class Role implements Serializable {

    private static final long serialVersionUID = -2297694601311152102L;

    /** 自增 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 名称 */
    private String name;

    /** 关联的人员集合 */
    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_user", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<User> users = new HashSet<>();

    /** 权限列表 */
    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_role_permission", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Permission> permissions = new HashSet<>();

    /** 创建时间 */
    @Column(name = "create_time")
    private Date createTime;

    /** 是否已删除 */
    private boolean deleted;

}
