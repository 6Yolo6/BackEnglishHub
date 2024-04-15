package com.example.englishhub.config;

import com.example.englishhub.exception.JwtValidationException;
import com.example.englishhub.utils.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: hahaha
 * @Date: 2024/4/11 14:10
 */

@Slf4j
// 全局异常处理类
@ControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.info("GlobalExceptionHandler 已加载");
    }

    @ExceptionHandler(JwtValidationException.class)
    @ResponseBody
    public ResponseEntity<?> handleJwtValidationException(JwtValidationException e) {
        // 创建一个响应对象，将异常中的状态码和消息设置到响应中
        log.error("JwtValidationException: {}", e.getMessage());
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", e.getStatusCode());
        response.put("message", e.getMessage());

        // 返回响应
        return ResponseEntity.status(Integer.parseInt(e.getStatusCode())).body(response);
    }
}


