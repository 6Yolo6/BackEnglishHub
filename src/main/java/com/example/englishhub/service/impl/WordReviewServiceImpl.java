package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.ForgettingCurve;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;
import com.example.englishhub.mapper.WordReviewMapper;
import com.example.englishhub.service.ForgettingCurveService;
import com.example.englishhub.service.LearningPlansService;
import com.example.englishhub.service.WordReviewService;
import com.example.englishhub.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: hahaha
 * @Date: 2024/4/8 15:18
 */

@Service
public class WordReviewServiceImpl extends ServiceImpl<WordReviewMapper, WordReview> implements WordReviewService {


    @Autowired
    private WordReviewMapper wordReviewMapper;

    @Autowired
    private LearningPlansService learningPlansService;

    @Autowired
    private ForgettingCurveService forgettingCurveService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void adjustReviewIntervals(Integer wordId, Integer wordBookId, Integer status) {
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);
        QueryWrapper<WordReview> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("word_id", wordId);
        queryWrapper.eq("user_id", Integer.parseInt(userId));
        queryWrapper.eq("word_book_id", wordBookId);

        WordReview wordReview = this.getOne(queryWrapper);

        if (wordReview == null) {
            // 新学单词，使用默认的复习间隔并新增记录
            String defaultIntervals = "30,180,720,1440,2880,5760,10080,21600";
            List<Integer> intervals = Arrays.stream(defaultIntervals.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            wordReview = new WordReview();
            wordReview.setUserId(Integer.parseInt(userId));
            wordReview.setWordId(wordId);
            wordReview.setWordBookId(wordBookId);
            wordReview.setStatus(status);
            wordReview.setReviewIntervals(defaultIntervals);
            wordReview.setReviewIntervalIndex(0);

            // 计算调整因子
            double factor = calculateAdjustmentFactor(status);
            // 调整复习间隔，将intervals列表中的每个元素乘以调整因子，然后将结果转换为整数。
            List<Integer> adjustedIntervals = intervals.stream()
                    .map(interval -> (int) (interval * factor))
                    .collect(Collectors.toList());
            wordReview.setReviewIntervals(adjustedIntervals.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(",")));

            // 设置下次复习时间
            if (status == 4) { // 单词已掌握
                wordReview.setNextReviewTime(null);
            } else {
                int newIndex = 0;
                wordReview.setReviewIntervalIndex(newIndex);
                wordReview.setNextReviewTime(LocalDateTime.now().plusMinutes(adjustedIntervals.get(newIndex)));
            }
            this.save(wordReview);
        } else {
            List<Integer> intervals = Arrays.stream(wordReview.getReviewIntervals().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            if (status == 4) { // 单词已掌握
                // 不再进行复习
                wordReview.setNextReviewTime(null);
            } else {
                double factor = calculateAdjustmentFactor(status);
                // 调整复习间隔，将intervals列表中的每个元素乘以调整因子，然后将结果转换为整数。
                List<Integer> adjustedIntervals = intervals.stream()
                        .map(interval -> (int) (interval * factor))
                        .collect(Collectors.toList());
                // 更新复习间隔，将adjustedIntervals列表中的每个元素转换为字符串，然后使用逗号（,）将这些字符串连接起来。
                wordReview.setReviewIntervals(adjustedIntervals.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(",")));

                // 确保索引在合理范围内
                int newIndex = (wordReview.getReviewIntervalIndex() + 1) % adjustedIntervals.size();
                wordReview.setReviewIntervalIndex(newIndex);
                wordReview.setNextReviewTime(LocalDateTime.now().plusMinutes(adjustedIntervals.get(newIndex)));

                this.updateById(wordReview);
            }
        }
    }

    private WordReview getByWordId(Integer wordId) {
        QueryWrapper<WordReview> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wordId", wordId);
        return this.getOne(queryWrapper);
    }


    @Override
    public List<WordReviewVO> getWordsToday(Integer wordBookId, Integer dailyNewWords, Integer dailyReviewWords) {
        // 调用mapper层方法获取今天的所有复习记录
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        List<WordReviewVO> reviewWords = wordReviewMapper.getReviewWordsToday(Integer.parseInt(userId), wordBookId, dailyReviewWords);
        List<WordReviewVO> newWords = wordReviewMapper.getNewWordsToday(Integer.parseInt(userId), wordBookId, dailyNewWords);
        reviewWords.addAll(newWords);
        return reviewWords;
    }

    @Override
    public List<WordReview> getAllWordsForUser(Integer userId) {
//        String token = request.getHeader("token");
//        String userId = JwtUtil.validateToken(token);
        QueryWrapper<WordReview> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        return this.list(queryWrapper);
    }

    public List<WordReview> getWordsByUserId() {
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        return getAllWordsForUser(Integer.parseInt(userId));
    }

    public double calculateAdjustmentFactor(int status) {
        double forgettingRate;
        switch (status) {
            case 1: // 忘记forgotten
                forgettingRate = 0.5;
                break;
            case 2: // 模糊 blurry
                forgettingRate = 0.3;
                break;
            case 3: // 熟悉 known
                forgettingRate = 0.1;
                break;
            default: // 已掌握 mastered
                forgettingRate = 0.0;
        }
        // 使用指数函数来计算调整因子，遗忘率越大，调整因子越小
        // 原型是e^(-x)
        return Math.exp(-forgettingRate);
    }

}
