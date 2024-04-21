package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.ForgettingCurve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.WordReview;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-19
 */
public interface ForgettingCurveService extends IService<ForgettingCurve> {


    void saveForgettingCurveData(WordReview wordReview, double retentionRate);

    Page<ForgettingCurve> getPage(Integer pageNum, Integer pageSize);

    void calculateRetentionRatesAndSave(List<WordReview> allWords);
}
