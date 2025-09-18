package com.agritrade.platform.controller;
import com.agritrade.platform.common.Result;
import com.agritrade.platform.dto.request.UserLoginRequest;
import com.agritrade.platform.dto.request.UserRegisterRequest;
import com.agritrade.platform.dto.response.LoginResponse;
import com.agritrade.platform.dto.response.UserResponse;
import com.agritrade.platform.entity.User;
import com.agritrade.platform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * 
 * @author MikeCOjike
 */
@Tag(name = "用户管理", description = "用户注册、登录、管理等功能")
@RestController
@RequestMapping("/users")
public class UserController {
    
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @Operation(summary = "用户注册", description = "新用户注册接口")
    @PostMapping("/register")
    public Result<UserResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        try {
            UserResponse user = userService.register(request);
            return Result.success("注册成功", user);
        } catch (Exception e) {
            log.error("用户注册失败: {}", e.getMessage());
            return Result.badRequest(e.getMessage());
        }
    }
    
    @Operation(summary = "用户登录", description = "用户登录接口")
    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            return Result.success("登录成功", response);
        } catch (Exception e) {
            log.error("用户登录失败: {}", e.getMessage());
            return Result.unauthorized(e.getMessage());
        }
    }
    
    @Operation(summary = "获取当前用户信息", description = "根据JWT令牌获取当前登录用户信息")
    @GetMapping("/profile")
    public Result<UserResponse> getCurrentUser(
            @Parameter(hidden = true) @RequestHeader("Authorization") String token) {
        // TODO: 从JWT令牌中解析用户信息
        // 这里先返回示例数据，后续集成JWT认证后完善
        return Result.success("获取用户信息成功", null);
    }
    
    @Operation(summary = "分页查询用户", description = "分页查询用户列表")
    @GetMapping
    public Result<Page<UserResponse>> getUsers(
            @Parameter(description = "页码，从0开始") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "排序字段") @RequestParam(defaultValue = "createdAt") String sort,
            @Parameter(description = "排序方向") @RequestParam(defaultValue = "desc") String direction,
            @Parameter(description = "用户类型筛选") @RequestParam(required = false) User.UserType userType,
            @Parameter(description = "关键词搜索") @RequestParam(required = false) String keyword) {
        
        try {
            Sort sortObj = Sort.by(Sort.Direction.fromString(direction), sort);
            Pageable pageable = PageRequest.of(page, size, sortObj);
            
            Page<UserResponse> users;
            if (keyword != null && !keyword.trim().isEmpty()) {
                users = userService.searchUsers(keyword, pageable);
            } else if (userType != null) {
                users = userService.findByUserType(userType, pageable);
            } else {
                users = userService.findAll(pageable);
            }
            
            return Result.success("查询成功", users);
        } catch (Exception e) {
            log.error("查询用户失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
    
    @Operation(summary = "根据ID查询用户", description = "根据用户ID查询用户详细信息")
    @GetMapping("/{id}")
    public Result<UserResponse> getUserById(@PathVariable Long id) {
        try {
            UserResponse user = userService.findById(id)
                    .orElseThrow(() -> new RuntimeException("用户不存在"));
            return Result.success("查询成功", user);
        } catch (Exception e) {
            log.error("查询用户失败: {}", e.getMessage());
            return Result.notFound(e.getMessage());
        }
    }
    
    @Operation(summary = "更新用户状态", description = "更新用户状态（激活/锁定等）")
    @PutMapping("/{id}/status")
    public Result<Void> updateUserStatus(
            @PathVariable Long id,
            @Parameter(description = "用户状态") @RequestParam User.UserStatus status) {
        try {
            userService.updateUserStatus(id, status);
            return Result.success("状态更新成功", null);
        } catch (Exception e) {
            log.error("更新用户状态失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
    
    @Operation(summary = "删除用户", description = "删除指定用户")
    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return Result.success("删除成功", null);
        } catch (Exception e) {
            log.error("删除用户失败: {}", e.getMessage());
            return Result.error(e.getMessage());
        }
    }
}
