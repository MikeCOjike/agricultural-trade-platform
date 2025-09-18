package com.agritrade.platform.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户登录请求DTO
 * 
 * @author MikeCOjike
 */
@Data
public class UserLoginRequest {
    
    @NotBlank(message = "用户名不能为空")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    private String password;
}
