package com.agritrade.platform.controller;

import com.agritrade.platform.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 * 
 * @author MikeCOjike
 */
@Tag(name = "测试接口", description = "用于测试系统是否正常运行")
@RestController
@RequestMapping("/test")
public class TestController {
    
    @Operation(summary = "健康检查", description = "检查系统是否正常运行")
    @GetMapping("/health")
    public Result<Map<String, Object>> health() {
        Map<String, Object> data = new HashMap<>();
        data.put("status", "UP");
        data.put("timestamp", LocalDateTime.now());
        data.put("message", "农产品跨境贸易云平台运行正常");
        data.put("version", "1.0.0");
        
        return Result.success("系统运行正常", data);
    }
    
    @Operation(summary = "Hello World", description = "简单的Hello World接口")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello, 农产品跨境贸易云平台!");
    }
}

