package com.cuckoom.blog.user.api;

import java.security.Principal;

import com.cuckoom.blog.common.PermissionConsts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cuckoom.blog.security.SecurityUtils;
import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

/**
 * 用户 API 控制器
 * @author cuckooM
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    /** 用户业务逻辑层接口 */
    private final UserService userService;

    /**
     * 查询当前用户
     * @param principal 当前用户信息
     * @return 结果
     */
    @GetMapping("/current")
    public ResponseEntity<UserDTO> current(Principal principal) {
        return ResponseEntity.ok(userService.findById(SecurityUtils.getUserId(principal)));
    }

    /**
     * 分页查询
     * @param pageable 分页条件
     * @return 数据
     */
    @GetMapping("/page")
    @PreAuthorize(PermissionConsts.PERM_USER_BASIC)
    public ResponseEntity<Page<UserDTO>> page(Pageable pageable) {
        return ResponseEntity.ok(userService.page(pageable));
    }

    /**
     * 增加用户
     * @param dto 用户实体
     * @return 结果
     */
    @PostMapping
    @PreAuthorize(PermissionConsts.PERM_USER_BASIC)
    public ResponseEntity<UserDTO> add(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.add(dto));
    }

    /**
     * 修改用户
     * @param id 用户 ID
     * @param dto 用户实体
     * @return 结果
     */
    @PutMapping("/{id}")
    @PreAuthorize(PermissionConsts.PERM_USER_BASIC)
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    /**
     * 删除用户
     * @param id 用户 ID
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize(PermissionConsts.PERM_USER_BASIC)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}