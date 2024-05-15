package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.LearningPlans;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;

import java.util.List;

/**
 * @Author: hahaha
 * @Date: 2024/4/8 15:14
 */
public interface WordReviewService extends IService<WordReview> {


    void adjustReviewIntervals(Integer wordId, Integer wordBookId);

    List<WordReviewVO> getWordsToday(Integer wordBookId, Integer dailyNewWords, Integer dailyReviewWords);

    List<WordReview> getAllWordsForUser(Integer userId);
}
