package com.cuckoom.blog.manage.label.utils;

import com.cuckoom.blog.manage.label.dto.LabelDTO;
import com.cuckoom.blog.manage.label.entity.Label;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

/**
 * 工具类
 * @author cuckooM
 */
public class LabelUtils {

    /** 防止实例化 */
    private LabelUtils() {
    }

    /**
     * 实体转化
     * @param dto DTO 实体
     * @return 数据库实体
     */
    @NonNull
    public static Label convert(@NonNull LabelDTO dto) {
        Label entity = new Label();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }

    /**
     * 实体转化
     * @param entity 数据库实体
     * @return DTO 实体
     */
    @NonNull
    public static LabelDTO convert(@NonNull Label entity) {
        LabelDTO dto = new LabelDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
