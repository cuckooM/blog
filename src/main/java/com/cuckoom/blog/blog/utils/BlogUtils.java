package com.cuckoom.blog.blog.utils;

import com.cuckoom.blog.blog.dto.BlogDTO;
import com.cuckoom.blog.blog.entity.Blog;
import com.cuckoom.blog.blog.entity.BlogDiscuss;
import com.cuckoom.blog.blog.vo.BlogDiscussVO;
import com.cuckoom.blog.blog.vo.BlogVO;
import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.label.entity.Label;
import com.cuckoom.blog.label.utils.LabelUtils;
import com.cuckoom.blog.user.dto.UserDTO;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        BeanUtils.copyProperties(source, target, "id", "authorId", "createTime", "updateTime", "labels");
        List<LabelDTO> labels = source.getLabels();
        if (null != labels) {
            target.setLabels(labels.stream().map(item -> {
                Label label = new Label();
                BeanUtils.copyProperties(item, label);
                return label;
            }).collect(Collectors.toList()));
        }
    }

    /**
     * 将数据库实体转化为 DTO 实体
     * @param entity 数据库实体
     * @param user 用户
     * @return DTO 实体
     */
    @NonNull
    public static BlogDTO toDTO(@NonNull Blog entity, @Nullable UserDTO user) {
        BlogDTO dto = new BlogDTO();
        BeanUtils.copyProperties(entity, dto, "labels");
        dto.setAuthor(user);
        if (null != entity.getLabels()) {
            dto.setLabels(entity.getLabels().stream().map(LabelUtils::convert).collect(Collectors.toList()));
        }
        return dto;
    }

    /**
     * 将数据库实体转化为 DTO 实体
     * @param entity 数据库实体
     * @param users 用户列表
     * @return DTO 实体
     */
    @NonNull
    public static BlogVO toVO(
        @NonNull Blog entity, @Nullable Collection<UserDTO> users) {
        BlogVO dto = new BlogVO();
        BeanUtils.copyProperties(entity, dto, "labels");
        if (null != users) {
            for (UserDTO user : users) {
                if (user.getId().equals(entity.getAuthorId())) {
                    dto.setAuthor(user);
                    break;
                }
            }
        }
        if (null != entity.getLabels()) {
            dto.setLabels(entity.getLabels().stream().map(LabelUtils::convert).collect(Collectors.toList()));
        }
        return dto;
    }

    /**
     * 将数据实体转化为 VO 实体
     * @param entity 数据实体
     * @return VO 实体
     */
    public static BlogDiscussVO toVO(@NonNull BlogDiscuss entity) {
        BlogDiscussVO vo = new BlogDiscussVO();
        if (entity.isDeleted()) {
            // 已删除数据
        } else {
            // 未删除数据
        }
        return vo;
    }

}
