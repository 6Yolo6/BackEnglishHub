package com.example.englishhub.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.User;
import com.example.englishhub.service.UserService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.MD5Util;
import com.example.englishhub.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 修改头像
     */
    @PostMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam(value = "file") MultipartFile file, Integer userId) {
        Result result = new Result();
//        fileUpload fileUpload=new fileUpload();
//        String url= fileUpload.upload(file);
//        if (userService.updateAvatar(userId, url)) {
//            result.success("修改头像成功");
//            result.setData(url);
//        } else {
//            result.fail("修改头像失败");
//        }
        return result;
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user) throws Exception {

        Result result = new Result();
//        // 检查用户名是否已存在
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getUsername, user.getUsername());
//        User existUser = userService.getOne(queryWrapper);
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
    @PostMapping("/login")
    public Result login(@RequestBody User user) throws Exception {
        System.out.println(user);

        Result result = new Result();
        User isUser = userService.getByName(user.getUsername());
        System.out.println(isUser);

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
                map.put("Authorization", JwtUtil.generateToken((isUser.getId()).toString()));
                result.setData(map);

            }
            else
                result.againLogin("密码错误");
        }

        return result;
    }


    @PostMapping("/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();

        userService.deleteByIds(ids);

        result.success("删除成功");

        return result;
    }

    /**
     * 修改用户信息
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        Result result=new Result();
//        // 从Session中获取Token
//        String token = (String) request.getSession().getAttribute("token");
//        // 验证Token的有效性
//        String userId = JwtUtil.validateToken(token);
//        if (StringUtils.isBlank(userId)) {
//            result.fail("Token无效，请重新登录");
//            return result;
//        }
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
            result.setData(isUser);
            result.success("更新成功");
        }
        else {
            result.fail("更新失败");
        }
        return result;
    }


    @GetMapping("/validate")
    public Result validate() {
        Result result = new Result();
        String token = request.getHeader("token");
//        System.out.println("token" + token);
        String userId = JwtUtil.validateToken(token);
        if (StringUtils.isBlank(userId)) {
            result.fail("Token无效，请重新登录");
            return result;
        }
        result.success("Token有效");
        return result;
    }

    @GetMapping("/getAll")
    public Result getAllUser(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<User> page = userService.getAllUser(pageNum, pageSize);
        List<User> user = page.getRecords();
        for (User user1:user) {
            user1.setPassword("");
        }
        result.setData(page);
        result.success("查询成功");
        return result;
    }

    /**
     * 根据用户 ID 查询用户信息
     */
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

    /**
     * 删除用户信息
     */
    @DeleteMapping("/deleteById")
    public Result deleteById() {
        Result result=new Result();
        String token = request.getHeader("token");
//        System.out.println("token" + token);
        String userId = JwtUtil.validateToken(token);
        if (StringUtils.isBlank(userId)) {
            result.fail("Token无效，请重新登录");
            return result;
        }

        // 验证用户权限
        User loginUser = userService.getById(Long.valueOf(userId));
        if (!loginUser.getIsAdmin()) {
            result.fail("您没有权限删除用户信息");
            return result;
        }

        userService.removeById(Integer.valueOf(userId));
        result.success("用户信息删除成功");
        return result;
    }

    //根据用户名查询用户信息
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
