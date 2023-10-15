package com.cuckoom.blog.user.api;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.service.UserService;

import lombok.RequiredArgsConstructor;

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
        return ResponseEntity.ok(null);
    }

}
