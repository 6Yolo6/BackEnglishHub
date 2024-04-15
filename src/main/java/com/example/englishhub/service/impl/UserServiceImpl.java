package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.User;
import com.example.englishhub.mapper.UserMapper;
import com.example.englishhub.service.UserService;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.MD5Util;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2023-06-09 10:14:35
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
    public Page<User> getAllUser(Integer pageNum, Integer pageSize) {

        Page<User> page = new Page<>(pageNum, pageSize);
        return this.page(page);
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }

    @Override
    public Boolean updateAvatar(Integer userId, String avatar) {
        User user = this.getById(userId);
        user.setAvatar(avatar);
        this.updateById(user);
        return true;
    }

    @Override
    public User updateUser(User user) throws Exception {
//        String token = request.getHeader("Authorization");
//        System.out.println("token" + token);
        User isUser = this.getById(user.getId());
//        System.out.println(isUser);
        if (user.getPassword() != "") {//重置密码
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
