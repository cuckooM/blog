package com.cuckoom.blog.web.utils;

import com.cuckoom.blog.security.TokenUser;
import org.springframework.lang.NonNull;

import java.security.Principal;

/**
 * Web 工具类
 * @author cuckooM
 */
public class WebUtils {

    /** 防止实例化 */
    private WebUtils() {
    }

    /**
     * 获取用户 ID
     * @param principal 用户登录信息
     * @return 结果
     */
    @NonNull
    public static Long getUserId(@NonNull Principal principal) {
        return ((TokenUser) principal).getUserId();
    }

}
