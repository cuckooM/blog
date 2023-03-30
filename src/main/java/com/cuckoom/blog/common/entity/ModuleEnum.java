package com.cuckoom.blog.common.entity;

/**
 * 模块枚举
 * @author cuckooM
 */
public enum ModuleEnum {

    /** 博客 */
    BLOG(0),

    /** 用户 */
    USER(1);

    /** 值 */
    private int value;

    /**
     * 构造函数
     * @param value 值
     */
    ModuleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
