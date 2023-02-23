package com.cuckoom.blog.manage.utils;

import com.cuckoom.blog.manage.dto.BlogDTO;
import com.cuckoom.blog.manage.entity.Blog;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;

import java.util.Date;

/**
 * 博客工具类
 * @author cuckooM
 */
public class BlogUtils {

    /** 防止实例化 */
    private BlogUtils() {
    }

    /**
     * 创建博客实体
     * @param dto DTO 实体
     * @param userId 作者用户 ID
     * @return 结果
     */
    @NonNull
    public static Blog create(@NonNull BlogDTO dto, @NonNull Long userId) {
        Blog entity = new Blog();
        BlogUtils.copyProperties(dto, entity);
        entity.setAuthorId(userId);
        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        return entity;
    }

    /**
     * 将 DTO 实体数据赋值给数据库实体
     * @param source DTO 实体
     * @param target 数据库实体
     */
    public static void copyProperties(@NonNull BlogDTO source, @NonNull Blog target) {
        BeanUtils.copyProperties(source, target, "id", "authorId", "createTime", "updateTime");
    }

    /**
     * 将数据库实体转化为 DTO 实体
     * @param entity 数据库实体
     * @return DTO 实体
     */
    @NonNull
    public static BlogDTO toDTO(@NonNull Blog entity) {
        BlogDTO dto = new BlogDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

}
