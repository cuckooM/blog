package com.cuckoom.blog.role.api;

import com.cuckoom.blog.common.PermissionConsts;
import com.cuckoom.blog.role.dto.PermissionDTO;
import com.cuckoom.blog.role.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/permission")
@RequiredArgsConstructor
@PreAuthorize(PermissionConsts.PERM_ROLE_BASIC)
public class PermissionController {

    /** 权限业务逻辑层接口 */
    private final PermissionService permissionService;

    /**
     * 列表查询，按层级组装数据。
     * @return 数据
     */
    @GetMapping
    public ResponseEntity<List<PermissionDTO>> list() {
        return ResponseEntity.ok(permissionService.list());
    }

}
