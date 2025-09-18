package com.agritrade.platform.dto.response;

import com.agritrade.platform.entity.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户响应DTO
 * 
 * @author MikeCOjike
 */
@Data
public class UserResponse {
    
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String realName;
    private User.UserType userType;
    private User.UserStatus status;
    private String avatarUrl;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createdAt;
    
    /**
     * 从User实体转换为UserResponse
     */
    public static UserResponse fromEntity(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRealName(user.getRealName());
        response.setUserType(user.getUserType());
        response.setStatus(user.getStatus());
        response.setAvatarUrl(user.getAvatarUrl());
        response.setLastLoginTime(user.getLastLoginTime());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
