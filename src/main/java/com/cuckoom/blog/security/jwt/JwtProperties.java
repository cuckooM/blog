package com.cuckoom.blog.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * JWT 配置参数
 * @author cuckooM
 */
@ConfigurationProperties(prefix = "cuckoom.blog.jwt")
@Getter
@Setter
public class JwtProperties {

    /** Base64 格式密钥 */
    private String secretBase64;

    /** 有效时间（秒） */
    private Long expiration;

    /** "remember-me"有效时间（秒） */
    private Long expirationRememberMe;

}
