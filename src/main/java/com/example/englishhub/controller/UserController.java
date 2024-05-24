package com.example.englishhub.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBooks;
import com.example.englishhub.entity.User;
import com.example.englishhub.service.UserService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.MD5Util;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.englishhub.utils.Upload;

//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 上传头像
     */
    @Operation(summary = "上传头像")
    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(@RequestParam(value = "file") MultipartFile file,
                               @RequestParam(value = "id") Integer id) {
        Result result = new Result();
        Upload fileUpload=new Upload();
        String url= fileUpload.upload(file);
        if (url != null) {
            if (id != null) {
                // 更新操作
                User existingUser= userService.getById(id);
                if (existingUser != null) {
                    // 删除原来的文件
                    fileUpload.delete(existingUser.getAvatar());
                } else {
                    result.fail("头像不存在");
                }
            }
            result.success("上传头像成功");
            result.setData(url);
        } else {
            result.fail("上传头像失败");
        }
        return result;
    }

    /**
     * 用户注册
     */
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody User user) throws Exception {

        Result result = new Result();
        // 检查用户名是否已存在
        User existUser = userService.getByName(user.getUsername());
        if (existUser != null) {
            result.fail("用户名已存在");
            return result;
        }
        else {
            result.setData(userService.register(user));
            result.success("注册成功");
        }
        return result;
    }

    /**
     * 用户登录
     */
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody User user) throws Exception {
//        System.out.println(user);

        Result result = new Result();
        User isUser = userService.getByName(user.getUsername());
//        System.out.println(isUser);

        if (isUser == null) {
            result.againLogin("用户名不正确");
            return result;
        }
        else {
            String password = MD5Util.getEncode(user.getPassword(), isUser.getSalt());
            if(password.equals(isUser.getPassword())) {
                result.success("登录成功");
                Map<String,Object> map = new HashMap<>();
                isUser.setSalt(null);
                isUser.setPassword(null);
                map.put("user", isUser);
                map.put("token", JwtUtil.generateToken((isUser.getId()).toString()));
                result.setData(map);
                // 标记用户为活跃用户
                userService.markUserActive(isUser.getId());
                log.info("用户登录成功");
            }
            else
                result.againLogin("密码错误");
        }

        return result;
    }


    /**
     * 新增用户
     */

    /**
     * 修改用户信息
     */
    @Operation(summary = "修改用户信息")
    @PostMapping("/update")
    public Result update(@RequestBody User user) throws Exception {
        Result result=new Result();
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        // 验证用户权限
        User loginUser = userService.getById(Integer.valueOf(userId));
        if (!loginUser.getIsAdmin()) {
            result.fail("您没有权限修改其他用户的信息");
            return result;
        }
        User isUser = userService.updateUser(user);
        if (isUser != null) {
            isUser.setPassword("");
            isUser.setSalt("");
            result.setData(isUser);
            result.success("更新成功");
        }
        else {
            result.fail("更新失败");
        }
        return result;
    }

    @Operation(summary = "修改用户信息")
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody Map<String, String> updates) {
        Result result = new Result();
        try {
            User user = userService.updateU(updates);
            result.setData(user);
            result.success("更新成功");

        } catch (Exception e) {
            result.fail("更新失败");
        }
        return result;
    }

    /**
     * 验证Token
     */
    @Operation(summary = "验证Token")
    @GetMapping("/validate")
    public Result validate() {
        Result result = new Result();
        String token = request.getHeader("token");
        System.out.println("token" + token);
        JwtUtil.validateToken(token);
        result.success("Token有效");
        return result;
    }

    /**
     * 获取所有用户信息
     */
    @Operation(summary = "获取所有用户信息")
    @GetMapping("/getAll")
    public Result getAllUser(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<User> page = userService.page(new Page<>(pageNum, pageSize));
        List<User> user = page.getRecords();
        // 隐藏密码，盐值
        for (User user1:user) {
            user1.setPassword("");
            user1.setSalt("");
        }
        result.setData(page);
        result.success("查询成功");
        return result;
    }

    /**
     * 根据用户 ID 查询用户信息
     */
    @Operation(summary = "根据用户 ID 查询用户信息")
    @GetMapping("/getById")
    public Result getById() {
        Result result=new Result();
        String token = request.getHeader("token");
//        System.out.println("token" + token);
        String userId = JwtUtil.validateToken(token);
        if (StringUtils.isBlank(userId)) {
            result.fail("Token无效，请重新登录");
            return result;
        }
        User user = userService.getById(Integer.valueOf(userId));
        if (user != null) {
            // 验证用户权限
//            if (!user.getId().equals(Long.valueOf(userId)) && !userService.getById(Long.valueOf(userId)).getIsAdmin()) {
//                result.fail("您没有权限查询其他用户的信息");
//                return result;
//            }
            result.setData(user);
            return result;
        }
        result.fail("查询失败，用户不存在");
        return result;
    }


    //根据用户名查询用户信息
    @Operation(summary = "根据用户名查询用户信息")
    @GetMapping("/getByName")
    public Result getByName(String username) {
        Result result=new Result();
        User user = userService.getByName(username);
        if (user != null) {
            user.setPassword("");
            result.setData(user);
            result.success("查询成功");
        }
        else {
            result.fail("查询失败");
        }
        return result;
    }

    //获取用户id
    @Operation(summary = "获取用户id")
    @GetMapping("/getId")
    public Result getId() {
        Result result = new Result();
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);
        if (StringUtils.isBlank(userId)) {
            result.fail("Token无效，请重新登录");
            return result;
        }
        result.setData(userId);
        result.success("查询成功");
        return result;
    }
}
