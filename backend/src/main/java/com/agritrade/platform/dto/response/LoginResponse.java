package com.agritrade.platform.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录响应DTO
 * 
 * @author MikeCOjike
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    
    private String token;
    private String tokenType = "Bearer";
    private UserResponse user;
    
    public LoginResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }
}
