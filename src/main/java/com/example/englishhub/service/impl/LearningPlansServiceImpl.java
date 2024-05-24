package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.englishhub.entity.LearningPlans;
import com.example.englishhub.mapper.LearningPlansMapper;
import com.example.englishhub.service.LearningPlansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 学习计划表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-19
 */
@Service
public class LearningPlansServiceImpl extends ServiceImpl<LearningPlansMapper, LearningPlans> implements LearningPlansService {

    @Autowired
    private HttpServletRequest request;

    @Override
public boolean upsertPlan(Integer worBookId, Integer dailyNewWords, Integer dailyReviewWords, String endDate) {
    QueryWrapper<LearningPlans> queryWrapper = new QueryWrapper<>();
    String token = request.getHeader("token");
    String userId = JwtUtil.validateToken(token);
    queryWrapper.eq("word_book_id", worBookId);
    queryWrapper.eq("user_id", Integer.parseInt(userId));
    LearningPlans learningPlans = getOne(queryWrapper);
    if (learningPlans == null) {
        learningPlans = new LearningPlans();
        learningPlans.setUserId(Integer.parseInt(userId));
        learningPlans.setWordBookId(worBookId);
        learningPlans.setStartDate(java.time.LocalDateTime.now());
    }
    learningPlans.setDailyNewWords(dailyNewWords);
    learningPlans.setDailyReviewWords(dailyReviewWords);
    learningPlans.setEndDate(java.time.LocalDateTime.parse(endDate + " 00:00:00"));
    if (learningPlans.getId() == null) {
        return save(learningPlans);
    } else {
        return updateById(learningPlans);
    }
}
}
