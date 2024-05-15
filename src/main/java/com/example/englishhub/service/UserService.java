package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2023-06-09 10:14:35
 */
public interface UserService extends IService<User> {

    Map<String, Object> register(User user) throws Exception;

     User getByName(String username);

     Map<String, Object> login(User user);

    User updateUser(User user) throws Exception;

    void markUserActive(int userId);

    boolean isUserActive(int userId);

    List<Integer> getActiveUserIds();

    Boolean updateAvatar(Integer userId, String avatar);


}
