package com.cuckoom.blog.manage.blog.api;

import com.cuckoom.blog.blog.vo.BlogVO;
import com.cuckoom.blog.common.PermissionConsts;
import com.cuckoom.blog.blog.dto.BlogDTO;
import com.cuckoom.blog.blog.service.BlogService;
import com.cuckoom.blog.security.SecurityUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 博客 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/manage/blog")
@RequiredArgsConstructor
@PreAuthorize(PermissionConsts.PERM_BLOG_BASIC)
public class ManageBlogController {

    /** 博客业务逻辑 */
    private final BlogService blogService;

    /**
     * 分页查询数据
     * @param userName 作者名称
     * @param pageable 分页条件
     * @param params 查询条件
     * @return 结果
     */
    @GetMapping
    public ResponseEntity<Page<BlogVO>> page(
        @RequestParam(required = false) String userName, Pageable pageable,
        @RequestParam MultiValueMap<String, String> params) {
        return ResponseEntity.ok(blogService.page(pageable, userName, params.get("labels.idIn")));
    }

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

    /**
     * 删除博客
     * @param id ID
     * @param principal 当前登录人员信息
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id, Principal principal) {
        blogService.delete(id, SecurityUtils.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

}
