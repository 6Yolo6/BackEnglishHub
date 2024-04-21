package com.example.englishhub.service;

import com.example.englishhub.entity.LearningPlans;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学习计划表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-19
 */
public interface LearningPlansService extends IService<LearningPlans> {

    boolean addPlan(LearningPlans learningPlans);

    LearningPlans getByUserIdAndCategoryId(Integer userId, Integer wordBookCategoryId);
}
