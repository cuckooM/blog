package com.cuckoom.blog.role.api;

import com.cuckoom.blog.common.PermissionConsts;
import com.cuckoom.blog.role.dto.RoleDTO;
import com.cuckoom.blog.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
@PreAuthorize(PermissionConsts.PERM_ROLE_BASIC)
public class RoleController {

    /** 角色业务逻辑层接口 */
    private final RoleService roleService;

    /**
     * 分页查询
     * @param pageable 分页条件
     * @return 数据
     */
    @GetMapping("/page")
    public ResponseEntity<Page<RoleDTO>> page(Pageable pageable) {
        return ResponseEntity.ok(roleService.page(pageable));
    }

    /**
     * 增加
     * @param dto DTO 实体
     * @return 结果
     */
    @PostMapping
    public ResponseEntity<RoleDTO> add(@Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.ok(roleService.add(dto));
    }

    /**
     * 修改
     * @param id ID
     * @param dto DTO 实体
     * @return 结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.ok(roleService.update(id, dto));
    }

    /**
     * 删除
     * @param id ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
