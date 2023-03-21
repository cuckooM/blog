package com.cuckoom.blog.exception;

import org.springframework.lang.NonNull;

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
     * @param message 错误信息
     */
    public PayloadException(@NonNull String message) {
        super(message);
        this.payload = null;
    }

    /**
     * 构造函数
     * @param message 错误信息
     * @param cause 异常
     */
    public PayloadException(@NonNull String message, Throwable cause) {
        super(message, cause);
        this.payload = null;
    }

    /**
     * 构造函数
     * @param cause 异常
     */
    public PayloadException(Throwable cause) {
        super(cause);
        this.payload = null;
    }

    /**
     * 构造函数
     * @param message 错误信息
     * @param payload 负载数据
     */
    public PayloadException(@NonNull String message, @NonNull Serializable payload) {
        super(message);
        this.payload = payload;
    }

    /**
     * 构造函数
     * @param payload 负载数据
     */
    public PayloadException(@NonNull Serializable payload) {
        super();
        this.payload = payload;
    }

    public Serializable getPayload() {
        return payload;
    }
}
