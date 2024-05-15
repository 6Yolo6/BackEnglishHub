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
    public boolean saveOrUpdateProgress(Integer eBookId, String progress) {
        QueryWrapper<ReadingProgress> progressQueryWrapper = new QueryWrapper<>();
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);
        progressQueryWrapper.eq("user_id", Integer.parseInt(userId));
        progressQueryWrapper.eq("e_book_id", eBookId);
        // 判断是否有相同的阅读进度
        ReadingProgress existingProgress = this.getOne(progressQueryWrapper);
        if (existingProgress != null) {
            System.out.println("已有阅读进度，更新阅读进度");
            existingProgress.setProgress(progress);
            return this.updateById(existingProgress);
        } else {
            System.out.println("没有阅读进度，新增阅读进度");
            ReadingProgress newProgress = new ReadingProgress();
            newProgress.setUserId(Integer.parseInt(userId));
            newProgress.setEBookId(eBookId);
            newProgress.setProgress(progress);
            return this.save(newProgress);
        }
    }
}
