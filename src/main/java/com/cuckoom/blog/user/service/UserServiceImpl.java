package com.cuckoom.blog.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.cuckoom.blog.common.service.MessageService;
import com.cuckoom.blog.exception.PayloadException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuckoom.blog.user.dto.UserDTO;
import com.cuckoom.blog.user.entity.User;
import com.cuckoom.blog.user.repository.UserRepository;
import com.cuckoom.blog.user.utils.UserUtils;

import lombok.RequiredArgsConstructor;

/**
 * 用户业务逻辑层接口实现类
 * @author cuckooM
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /** 用户数据访问层接口 */
    private final UserRepository userRepository;

    /** 加密算法 */
    private final PasswordEncoder passwordEncoder;

    /** 多语言资源支持接口 */
    private final MessageService messageService;

    @Override
    @Transactional
    @NonNull
    public UserDTO add(@NonNull UserDTO dto) {
        // 保存数据
        User entity = UserUtils.convert(dto);
        entity.setId(null);
        entity.setPasswd(passwordEncoder.encode(dto.getPasswd()));
        entity.setCreateTime(new Date());
        entity = userRepository.save(entity);
        return UserUtils.convert(entity);
    }

    @Override
    @Transactional
    @NonNull
    public UserDTO update(@NonNull Long id, @NonNull UserDTO dto) {
        User entity = userRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new PayloadException(messageService.getMessage("error.not.exist"));
        }
        BeanUtils.copyProperties(dto, entity, "id", "passwd");
        if (null != dto.getPasswd()) {
            entity.setPasswd(passwordEncoder.encode(dto.getPasswd()));
        }
        entity = userRepository.save(entity);
        return UserUtils.convert(entity);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        User entity = userRepository.findById(id).orElse(null);
        if (null == entity) {
            throw new PayloadException(messageService.getMessage("error.not.exist"));
        }
        userRepository.delete(id);
    }

    @Override
    @Nullable
    public UserDTO findByUserName(@NonNull String userName) {
        User entity = userRepository.findTopByUserNameAndDeletedFalse(userName);
        if (null != entity) {
            return UserUtils.convert(entity);
        }
        return null;
    }

    @Override
    @Nullable
    public UserDTO findById(@NonNull Long userId) {
        return userRepository.findById(userId)
            .map(UserUtils::convert)
            .orElse(null);
    }

    @Override
    @NonNull
    public List<UserDTO> findByIds(@NonNull Collection<Long> userIds) {
        if (userIds.isEmpty()) {
            return new ArrayList<>();
        }
        Specification<User> spec = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            // ID 条件
            Predicate predicate = root.get("id").in(userIds);
            return query.where(predicate).getRestriction();
        };
        return userRepository.findAll(spec)
            .stream()
            .map(UserUtils::convert)
            .collect(Collectors.toList());
    }

    @Override
    @NonNull
    public Page<UserDTO> page(@NonNull Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return new PageImpl<>(page.getContent().stream().map(UserUtils::convert).collect(Collectors.toList()),
                pageable, page.getTotalElements());
    }

}
