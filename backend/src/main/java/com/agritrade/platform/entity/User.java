package com.agritrade.platform.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 用户实体类
 * 
 * @author MikeCOjike
 */
@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(callSuper = false)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    
    @Column(length = 20)
    private String phone;
    
    @Column(name = "real_name", length = 50)
    private String realName;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, length = 20)
    private UserType userType;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserStatus status = UserStatus.ACTIVE;
    
    @Column(name = "avatar_url")
    private String avatarUrl;
    
    @Column(name = "last_login_time")
    private LocalDateTime lastLoginTime;
    
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    /**
     * 用户类型枚举
     */
    public enum UserType {
        EXPORT_ENTERPRISE("出口企业"),
        IMPORT_ENTERPRISE("进口企业"), 
        LOGISTICS_PROVIDER("物流服务商"),
        REGULATORY_AUTHORITY("监管机构");
        
        private final String description;
        
        UserType(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 用户状态枚举
     */
    public enum UserStatus {
        ACTIVE("激活"),
        INACTIVE("未激活"),
        LOCKED("锁定");
        
        private final String description;
        
        UserStatus(String description) {
            this.description = description;
        }
        
        public String getDescription() {
            return description;
        }
    }
}

