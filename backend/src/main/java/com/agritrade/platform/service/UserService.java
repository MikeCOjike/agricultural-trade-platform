package com.agritrade.platform.service;

import com.agritrade.platform.dto.request.UserLoginRequest;
import com.agritrade.platform.dto.request.UserRegisterRequest;
import com.agritrade.platform.dto.response.LoginResponse;
import com.agritrade.platform.dto.response.UserResponse;
import com.agritrade.platform.entity.User;
import com.agritrade.platform.repository.UserRepository;
import com.agritrade.platform.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 用户服务类
 * 
 * @author MikeCOjike
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    /**
     * 用户注册
     */
    @Transactional
    public UserResponse register(UserRegisterRequest request) {
        log.info("用户注册: {}", request.getUsername());
        
        // 检查用户名是否已存在
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("邮箱已被注册");
        }
        
        // 创建用户实体
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRealName(request.getRealName());
        user.setUserType(request.getUserType());
        user.setStatus(User.UserStatus.ACTIVE);
        
        // 保存用户
        User savedUser = userRepository.save(user);
        log.info("用户注册成功: {}", savedUser.getUsername());
        
        return UserResponse.fromEntity(savedUser);
    }
    
    /**
     * 用户登录
     */
    @Transactional
    public LoginResponse login(UserLoginRequest request) {
        log.info("用户登录: {}", request.getUsername());
        
        // 查找用户
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("用户名或密码错误"));
        
        // 检查用户状态
        if (user.getStatus() == User.UserStatus.LOCKED) {
            throw new RuntimeException("账户已被锁定，请联系管理员");
        }
        
        if (user.getStatus() == User.UserStatus.INACTIVE) {
            throw new RuntimeException("账户未激活，请先激活账户");
        }
        
        // 验证密码
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 更新最后登录时间
        user.setLastLoginTime(LocalDateTime.now());
        userRepository.save(user);
        
        // 生成JWT令牌
        String token = jwtUtil.generateToken(
                user.getUsername(), 
                user.getId(), 
                user.getUserType().name()
        );
        
        log.info("用户登录成功: {}", user.getUsername());
        
        return new LoginResponse(token, UserResponse.fromEntity(user));
    }
    
    /**
     * 根据用户名查找用户
     */
    public Optional<UserResponse> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserResponse::fromEntity);
    }
    
    /**
     * 根据ID查找用户
     */
    public Optional<UserResponse> findById(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity);
    }
    
    /**
     * 分页查询用户
     */
    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserResponse::fromEntity);
    }
    
    /**
     * 根据用户类型分页查询
     */
    public Page<UserResponse> findByUserType(User.UserType userType, Pageable pageable) {
        return userRepository.findByUserType(userType, pageable)
                .map(UserResponse::fromEntity);
    }
    
    /**
     * 关键词搜索用户
     */
    public Page<UserResponse> searchUsers(String keyword, Pageable pageable) {
        return userRepository.findByKeyword(keyword, pageable)
                .map(UserResponse::fromEntity);
    }
    
    /**
     * 更新用户状态
     */
    @Transactional
    public void updateUserStatus(Long userId, User.UserStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        user.setStatus(status);
        userRepository.save(user);
        
        log.info("更新用户状态: {} -> {}", user.getUsername(), status);
    }
    
    /**
     * 删除用户
     */
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("用户不存在");
        }
        
        userRepository.deleteById(userId);
        log.info("删除用户: {}", userId);
    }
}
