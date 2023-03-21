package com.cuckoom.blog.manage.label.api;

import com.cuckoom.blog.common.PermissionConsts;
import com.cuckoom.blog.manage.label.dto.LabelDTO;
import com.cuckoom.blog.manage.label.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

/**
 * 标签 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/manage/label")
@RequiredArgsConstructor
@RolesAllowed(PermissionConsts.ROLE_MANAGER)
public class LabelController {

    /** 标签业务逻辑 */
    private final LabelService labelService;

    /**
     * 分页查询数据
     * @param pageable 分页条件
     * @return 结果
     */
    @GetMapping
    public ResponseEntity<Page<LabelDTO>> page(Pageable pageable) {
        return ResponseEntity.ok(labelService.page(pageable));
    }

}
