package com.cuckoom.blog.common.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 多语言资源支持接口
 * @author cuckooM
 */
public interface MessageService {

    /**
     * 获取资源
     * @param code 资源标志
     * @return 结果
     */
    String getMessage(@NonNull String code);

    /**
     * 获取资源
     * @param code 资源标志
     * @param args 变量替换参数
     * @return 结果
     */
    String getMessage(@NonNull String code, @Nullable String... args);

}
