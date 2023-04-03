package com.cuckoom.blog.blog.api;

import java.util.List;

import com.cuckoom.blog.blog.service.BlogService;
import com.cuckoom.blog.blog.vo.BlogVO;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * @param params 查询条件
     * @return 结果
     */
    @GetMapping
    public ResponseEntity<Page<BlogVO>> page(
        @RequestParam(required = false) Long authorId, Pageable pageable,
        @RequestParam MultiValueMap<String, String> params) {
        return ResponseEntity.ok(blogService.page(pageable, authorId, params.get("labels.idIn")));
    }

    /**
     * 根据 ID 查询数据
     * @param id ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public ResponseEntity<BlogVO> findOne(@PathVariable Long id) {
        BlogVO blog = blogService.findOne(id);
        if (null == blog) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(blog);
    }

}
