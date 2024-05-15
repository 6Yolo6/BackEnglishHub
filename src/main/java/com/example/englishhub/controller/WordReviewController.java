package com.example.englishhub.controller;

import com.example.englishhub.entity.LearningPlans;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;
import com.example.englishhub.service.WordReviewService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hahaha
 * @Date: 2024/4/8 15:13
 */

@RestController
@RequestMapping("/wordReview")
@Tag(name = "单词复习管理")
public class WordReviewController {

    @Autowired
    private WordReviewService wordReviewService;



    /**
     * 调整复习
     * @body wordReview
     */
    @Operation(summary = "调整复习")
    @PostMapping("/adjust")
    public Result adjustReviewIntervals(@RequestParam Integer wordId, @RequestParam Integer wordBookId) {
        wordReviewService.adjustReviewIntervals(wordId, wordBookId);
        Result result = new Result();
        result.success("调整复习成功");
        return result;
    }

    /**
     * 新增数据
     * @body wordReview 实体对象
     */


    /**
     * 获取当天需学单词
     * @param wordBookId 单词书ID
     * @param dailyNewWords 每日新学单词数
     * @param dailyReviewWords 每日复习单词数
     * @return 复习单词列表
     */
    @Operation(summary = "获取当天需学单词")
    @GetMapping("/getToday")
    public Result getToday(Integer wordBookId, Integer dailyNewWords, Integer dailyReviewWords) {
        Result result = new Result();
        List<WordReviewVO> wordReviews = wordReviewService.getWordsToday(wordBookId, dailyNewWords, dailyReviewWords);
        result.setData(wordReviews);
        result.success("获取当天复习单词成功");
        return result;
    }
}
