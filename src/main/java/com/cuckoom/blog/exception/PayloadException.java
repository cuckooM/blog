package com.cuckoom.blog.exception;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 带有负载的异常。
 * @author cuckooM
 */
public class PayloadException extends RuntimeException {
    private static final long serialVersionUID = -2227866671369001302L;

    /** 负载数据 */
    private final Serializable payload;

    /**
     * 构造函数
     */
    public PayloadException() {
        super();
        this.payload = null;
    }

    /**
     * 构造函数
     * @param t 异常
     */
    public PayloadException(Throwable t) {
        super(t);
        this.payload = null;
    }

    /**
     * 构造函数
     * @param payload 负载数据
     */
    public PayloadException(@Nullable Serializable payload) {
        super();
        this.payload = payload;
    }

}
