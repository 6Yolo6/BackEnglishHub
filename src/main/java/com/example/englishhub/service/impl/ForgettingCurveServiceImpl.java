package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.ForgettingCurve;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.mapper.ForgettingCurveMapper;
import com.example.englishhub.service.ForgettingCurveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-19
 */
@Service
public class ForgettingCurveServiceImpl extends ServiceImpl<ForgettingCurveMapper, ForgettingCurve> implements ForgettingCurveService {

    @Override
    public void saveForgettingCurveData(WordReview wordReview, double retentionRate) {
        ForgettingCurve forgettingCurve = new ForgettingCurve();
        forgettingCurve.setWordReviewId(wordReview.getId());
        forgettingCurve.setUserId(wordReview.getUserId());
        forgettingCurve.setRetentionRate(retentionRate);
        this.save(forgettingCurve);
    }


    @Override
    public Page<ForgettingCurve> getPage(Integer pageNum, Integer pageSize) {
        QueryWrapper<ForgettingCurve> queryWrapper = new QueryWrapper<>();
        Page<ForgettingCurve> page = new Page<>(pageNum, pageSize);
        return this.page(page, queryWrapper);
    }

    @Override
    public void calculateRetentionRatesAndSave(List<WordReview> allWords) {
        LocalDateTime now = LocalDateTime.now();

        for (WordReview review : allWords) {
            if (review.getStatus() != 4) { // 过滤掉已掌握的单词
                double retentionRate = calculateRetentionRate(review, now);
                saveForgettingCurveData(review, retentionRate);
            }
        }
    }


    private double calculateRetentionRate(WordReview review, LocalDateTime now) {
        if (review.getNextReviewTime() == null) {
            return 1.0; // 如果已掌握或未设置复习时间，假设记忆保持率完整
        }
        long minutesSinceLastReview = ChronoUnit.MINUTES.between((Temporal) review.getLastReviewTime(), now);
        long minutesUntilNextReview = ChronoUnit.MINUTES.between(now, review.getNextReviewTime());
        // 简化模型，使用对数减缓衰减速度
        double forgettingRate = Math.log(minutesUntilNextReview / 30.0 + 1);

        // 计算当前记忆保持率，遗忘率越大，记忆保持率越小
        return Math.exp(-forgettingRate * minutesSinceLastReview / minutesUntilNextReview);
    }
}
