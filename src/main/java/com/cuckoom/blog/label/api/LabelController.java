package com.cuckoom.blog.label.api;

import java.util.List;

import com.cuckoom.blog.label.service.LabelService;
import com.cuckoom.blog.label.dto.LabelDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 标签 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/label")
@RequiredArgsConstructor
public class LabelController {

    /** 标签业务逻辑 */
    private final LabelService labelService;

    /**
     * 查询列表数据
     * @return 结果
     */
    @GetMapping
    public ResponseEntity<List<LabelDTO>> list() {
        return ResponseEntity.ok(labelService.page(Pageable.unpaged()).getContent());
    }

}
