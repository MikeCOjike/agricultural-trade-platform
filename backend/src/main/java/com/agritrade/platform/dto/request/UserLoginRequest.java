package com.agritrade.platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 用户登录请求DTO
 * 
 * @author MikeCOjike
 */
public class UserLoginRequest {
    
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    private String password;
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginRequest that = (UserLoginRequest) o;
        return Objects.equals(username, that.username);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
    
    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "username='" + username + '\'' +
                '}';
    }
}
