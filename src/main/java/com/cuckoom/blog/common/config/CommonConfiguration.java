package com.cuckoom.blog.common.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * 通用配置
 * @author cuckooM
 */
@Configuration
public class CommonConfiguration {

    /**
     * 多语言资源支持
     * @return MessageSource Bean 实例
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setDefaultLocale(Locale.CHINA);
        source.setDefaultEncoding("UTF-8");
        source.setBasename("com.cuckoom.blog.LocalStrings");
        return source;
    }

}
