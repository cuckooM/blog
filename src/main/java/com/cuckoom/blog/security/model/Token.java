package com.cuckoom.blog.security.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Token 实体
 * @author cuckooM
 */
@Getter
@Setter
public class Token implements Serializable {
    private static final long serialVersionUID = 4517666132110908792L;

    /** token 对象 */
    private String token;

    /** 有效时间。单位：毫秒 */
    private long expired;

    /**
     * 构造函数
     * @param token token 对象
     * @param expired 有效时间。单位：毫秒
     */
    public Token(String token, long expired) {
        this.token = token;
        this.expired = expired;
    }
}
