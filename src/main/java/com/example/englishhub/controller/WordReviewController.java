package com.example.englishhub.controller;

import com.example.englishhub.entity.LearningPlans;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.service.WordReviewService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
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
public class WordReviewController {

    @Autowired
    private WordReviewService wordReviewService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 调整复习
     * @body wordReview
     */
    @Operation(summary = "调整复习")
    @PostMapping("/adjust")
    public Result adjustReviewIntervals(@RequestBody WordReview wordReview) {
        wordReviewService.adjustReviewIntervals(wordReview);
        Result result = new Result();
        result.success("调整复习成功");
        return result;
    }

    /**
     * 新增数据
     * @body wordReview 实体对象
     */


    /**
     * 获取当天复习单词
     * @return 复习单词列表
     */
    @Operation(summary = "获取当天复习单词")
    @GetMapping("/getToday")
    public Result getToday() {
        Result result = new Result();
        List<WordReview> wordReviews = wordReviewService.getAllReviewsForToday();
        result.setData(wordReviews);
        result.success("获取当天复习单词成功");
        return result;
    }
}
