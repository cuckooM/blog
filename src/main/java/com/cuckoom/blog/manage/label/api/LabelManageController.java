package com.cuckoom.blog.manage.label.api;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuckoom.blog.common.PermissionConsts;
import com.cuckoom.blog.label.dto.LabelDTO;
import com.cuckoom.blog.label.service.LabelService;

import lombok.RequiredArgsConstructor;

/**
 * 标签管理 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/manage/label")
@RequiredArgsConstructor
@RolesAllowed(PermissionConsts.ROLE_MANAGER)
public class LabelManageController {

    /** 标签业务逻辑 */
    private final LabelService labelService;

    /**
     * 增加
     * @param dto DTO 实体
     * @return 结果
     */
    @PostMapping
    public ResponseEntity<LabelDTO> add(@RequestBody LabelDTO dto) {
        return ResponseEntity.ok(labelService.add(dto));
    }

    /**
     * 编辑
     * @param id ID
     * @param dto DTO 实体
     * @return 结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<LabelDTO> update(@PathVariable Long id, @RequestBody LabelDTO dto) {
        return ResponseEntity.ok(labelService.update(id, dto));
    }

    /**
     * 删除
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        labelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
