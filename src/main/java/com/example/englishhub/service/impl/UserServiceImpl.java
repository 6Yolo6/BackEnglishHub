package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.User;
import com.example.englishhub.mapper.UserMapper;
import com.example.englishhub.service.UserService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.MD5Util;
import com.example.englishhub.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2023-06-09 10:14:35
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RedisUtils redisUtils;

    @Cacheable(value = "myData", key = "#user.id")
    @Override
    public Map<String, Object> register(User user) throws Exception {
        // 设置默认头像
        user.setAvatar("https://hyf666.oss-cn-fuzhou.aliyuncs.com/english_hub/image/logo.png");
        // 设置默认为普通用户
        user.setIsAdmin(false);
        // 对密码进行加密
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        String password = MD5Util.getEncode(user.getPassword(), salt);
        user.setPassword(password);

        this.save(user);
        // 生成Token
        String token = JwtUtil.generateToken(user.getId().toString());
        Map<String, Object> map = new HashMap<>();
        // 将Token存储到Map中
        map.put("token", token);
        map.put("username", user.getUsername());
        return map;
    }

    @Override
    @Cacheable(value = "myData", key = "#username")
    public User getByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = this.getOne(wrapper);
        if (user != null) {
            return user;
        }
        return null;
    }


    @Override
    public void markUserActive(int userId) {
        // 只保留到分钟
        String key = "active_users:" + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        String key = "active_users:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        redisUtils.setBit(key, userId, true);
        log.info("用户id为{}的用户已标记活跃--{}", userId, key);
        // 设置2小时的过期时间
        redisUtils.expire(key, 2, TimeUnit.HOURS);
    }

    @Override
    public boolean isUserActive(int userId) {
        // 当前时间
        LocalDateTime currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        for (int i = 0; i < 2; i++) {
            // 遍历过去2小时内的键
            String key = "active_users:" + currentTime.minusHours(i).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (redisUtils.hasKey(key)) {
                Boolean isUserActive = redisUtils.getBit(key, userId);
                if (isUserActive != null && isUserActive) {
                    log.info("用户id为{}的用户在过去2小时内活跃过", userId);
                    return true;
                }
            }
        }
        log.info("用户id为{}的用户在过去2小时内未活跃", userId);
        return false;
    }

    @Override
    public List<Integer> getActiveUserIds() {
        List<Integer> activeUserIds = new ArrayList<>();

        // 当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        for (int i = 0; i < 2; i++) {
            // 遍历过去2小时内的键
            String key = "active_users:" + currentTime.minusHours(i).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            if (redisUtils.hasKey(key)) {
                // 遍历1000个用户
                for (int j = 0; j < 1000; j++) {
                    Boolean isUserActive = redisUtils.getBit(key, j);
                    if (isUserActive != null && isUserActive) {
                        activeUserIds.add(j);
                    }
                }
            }
        }

        return activeUserIds;
    }




    @Override
    public Boolean updateAvatar(Integer userId, String avatar) {
        User user = this.getById(userId);
        user.setAvatar(avatar);
        this.updateById(user);
        return true;
    }

    @Override
    public User updateU(Map<String, String> updates) {

        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        User user = this.getById(Integer.parseInt(userId));

        for (Map.Entry<String, String> entry : updates.entrySet()) {
            switch (entry.getKey()) {
                case "username":
                    user.setUsername(entry.getValue());
                    break;
                case "email":
                    user.setEmail(entry.getValue());
                    break;
                case "telephone":
                    user.setTelephone(entry.getValue());
                    break;
                case "avatar":
                    user.setAvatar(entry.getValue());
                    break;
                default:
                    throw new IllegalArgumentException("Invalid key: " + entry.getKey());
            }
        }



        return user;
    }


    @Override
    public User updateUser(User user) throws Exception {
//        String token = request.getHeader("Authorization");
//        System.out.println("token" + token);
        User isUser = this.getById(user.getId());
//        System.out.println(isUser);
        if (user.getPassword() != "") {//有密码，重置密码
            String salt = UUID.randomUUID().toString();
            isUser.setSalt(salt);
            String password = MD5Util.getEncode(user.getPassword(), salt);
            isUser.setPassword(password);
        }
        isUser.setAvatar(user.getAvatar());
        isUser.setEmail(user.getEmail());
        isUser.setTelephone(user.getTelephone());
        isUser.setSex(user.getSex());
        isUser.setUsername(user.getUsername());
        this.updateById(isUser);

        return isUser;
    }

    @Override
    public Map<String, Object> login(User user) {
//        String password = user.getPassword();
//        // 对密码进行加密
//        password = DigestUtils.md5DigestAsHex(password.getBytes());
//        result.success("登录成功");
//        // 生成Token
//        String token = JwtUtil.generateToken(loginUser.getId().toString());
//        Map<String, Object> map = new HashMap<>();
//        // 将Token存储到Map中
//        user.setPassword(null);
//        map.put("token", token);
//        map.put("user", loginUser);
        return null;
    }

}
