package com.cuckoom.blog.manage.api;

import com.cuckoom.blog.manage.dto.BlogDTO;
import com.cuckoom.blog.manage.service.BlogService;
import com.cuckoom.blog.web.utils.WebUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 博客 API 控制器
 */
@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {

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
        return ResponseEntity.ok(blogService.add(dto, WebUtils.getUserId(principal)));
    }

}
