package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.englishhub.entity.LearningPlans;
import com.example.englishhub.mapper.LearningPlansMapper;
import com.example.englishhub.service.LearningPlansService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    @Override
    public boolean addPlan(LearningPlans learningPlans) {
        return save(learningPlans);
    }

    @Override
    public LearningPlans getByUserIdAndCategoryId(Integer userId, Integer wordBookCategoryId) {
        QueryWrapper<LearningPlans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("word_book_category_id", wordBookCategoryId);
        return getOne(queryWrapper);
    }
}
