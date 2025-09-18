package com.agritrade.platform.dto.request;

import com.agritrade.platform.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户注册请求DTO
 * 
 * @author MikeCOjike
 */
@Data
public class UserRegisterRequest {
    
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 50, message = "用户名长度必须在3-50个字符之间")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
    private String password;
    
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    
    private String phone;
    
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    
    @NotNull(message = "用户类型不能为空")
    private User.UserType userType;
}
