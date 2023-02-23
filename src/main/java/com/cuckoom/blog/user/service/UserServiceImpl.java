package com.cuckoom.blog.user.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.entity.User;
import com.cuckoom.blog.user.repository.UserRepository;

/**
 * 用户业务逻辑层接口实现类
 * @author cuckooM
 */
@Service
public class UserServiceImpl implements UserService {

    /** 用户数据访问层接口 */
    private final UserRepository userRepository;

    /**
     * 构造函数
     * @param userRepository 用户数据访问层接口
     */
    public UserServiceImpl(@NonNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    @NonNull
    public UserDTO add(@NonNull UserDTO dto) {
        // 保存数据
        User entity = new User();
        BeanUtils.copyProperties(dto, entity, "id");
        entity.setCreateTime(new Date());
        entity = userRepository.save(entity);
        // 构造返回值
        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    @Override
    @Nullable
    public UserDTO findByUserName(@NonNull String userName) {
        User entity = userRepository.findTopByUserNameAndDeletedFalse(userName);
        if (null != entity) {
            UserDTO result = new UserDTO();
            BeanUtils.copyProperties(entity, result);
            return result;
        }
        return null;
    }

}
