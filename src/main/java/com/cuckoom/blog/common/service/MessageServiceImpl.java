package com.cuckoom.blog.common.service;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * 多语言资源支持
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    /** 多语言资源支持 */
    private final MessageSource messageSource;

    /** 默认语言 */
    private final Locale locale = Locale.CHINA;

    @Override
    public String getMessage(@NonNull String code) {
        return messageSource.getMessage(code, null, locale);
    }

    @Override
    public String getMessage(@NonNull String code, @Nullable String... args) {
        return messageSource.getMessage(code, args, locale);
    }

}
