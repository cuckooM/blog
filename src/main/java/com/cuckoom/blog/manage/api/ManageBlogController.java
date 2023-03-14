package com.cuckoom.blog.manage.api;

import com.cuckoom.blog.common.PermissionConsts;
import com.cuckoom.blog.manage.dto.BlogDTO;
import com.cuckoom.blog.manage.service.BlogService;
import com.cuckoom.blog.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import javax.annotation.security.RolesAllowed;

/**
 * 博客 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/manage/blog")
@RequiredArgsConstructor
@RolesAllowed(PermissionConsts.ROLE_MANAGER)
public class ManageBlogController {

    /** 博客业务逻辑 */
    private final BlogService blogService;

    /**
     * 增加博客
     * @param dto DTO 实体
     * @param principal 当前登录人员信息
     * @return 结果
     */
    @PostMapping
    public ResponseEntity<BlogDTO> add(@RequestBody BlogDTO dto, Principal principal) {
        return ResponseEntity.ok(blogService.add(dto, SecurityUtils.getUserId(principal)));
    }

    /**
     * 编辑博客
     * @param id ID
     * @param dto DTO 实体
     * @param principal 当前登录人员信息
     * @return 结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<BlogDTO> update(@PathVariable Long id, @RequestBody BlogDTO dto, Principal principal) {
        return ResponseEntity.ok(blogService.update(id, dto, SecurityUtils.getUserId(principal)));
    }

}
