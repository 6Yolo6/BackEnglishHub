package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.englishhub.entity.ReadingProgress;
import com.example.englishhub.mapper.ReadingProgressMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.service.ReadingProgressService;
import com.example.englishhub.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;

/**
 * <p>
 * 保存用户阅读进度 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-17
 */
@Service
public class ReadingProgressServiceImpl extends ServiceImpl<ReadingProgressMapper, ReadingProgress> implements ReadingProgressService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public ReadingProgress getProgress(Integer eBookId) {
        QueryWrapper<ReadingProgress> progressQueryWrapper = new QueryWrapper<>();

        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        progressQueryWrapper.eq("user_id", Integer.parseInt(userId));
        progressQueryWrapper.eq("e_book_id", eBookId);
        return this.getOne(progressQueryWrapper);
    }

    @Override
    public boolean addProgress(ReadingProgress progress) {
        QueryWrapper<ReadingProgress> progressQueryWrapper = new QueryWrapper<>();
        progressQueryWrapper.eq("user_id", progress.getUserId());
        progressQueryWrapper.eq("e_book_id", progress.getEBookId());
        ReadingProgress existingProgress = this.getOne(progressQueryWrapper);
        if (existingProgress != null) {
            throw new RuntimeException("用户阅读进度已存在");
        }
        return save(progress);
    }
}
