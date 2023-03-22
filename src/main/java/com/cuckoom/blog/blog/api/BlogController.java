package com.cuckoom.blog.blog.api;

import com.cuckoom.blog.blog.dto.BlogDTO;
import com.cuckoom.blog.blog.service.BlogService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class BlogController {

    /** 博客业务逻辑 */
    private final BlogService blogService;

    /**
     * 分页查询数据
     * @param authorId 作者 ID
     * @param pageable 分页条件
     * @return 结果
     */
    @GetMapping
    public ResponseEntity<Page<BlogDTO>> page(@RequestParam(required = false) Long authorId, Pageable pageable) {
        return ResponseEntity.ok(blogService.page(pageable, authorId));
    }

}