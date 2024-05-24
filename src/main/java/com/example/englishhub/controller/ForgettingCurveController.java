package com.example.englishhub.controller;


import com.example.englishhub.entity.WordReview;
import com.example.englishhub.service.ForgettingCurveService;
import com.example.englishhub.service.UserService;
import com.example.englishhub.service.WordReviewService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (ForgettingCurve)表控制层
 *
 * @author hahaha
 * @since 2024-04-19 21:24:11
 */

@RestController
@RequestMapping("/forgettingCurve")
@Tag(name = "遗忘曲线管理")
public class ForgettingCurveController {
    /**
     * 服务对象
     */
    @Autowired
    private ForgettingCurveService forgettingCurveService;

    @Autowired
    private WordReviewService wordReviewService;

    @Autowired
    private UserService userService;

    /**
     * 后台管理分页查询所有数据
     *
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        result.setData(forgettingCurveService.getPage(pageNum, pageSize));
        result.success("查询成功");
        return result;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */

    /**
     * 新增数据
     *
     * @param forgettingCurve 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param forgettingCurve 实体对象
     * @return 修改结果
     */


    /**
     * 计算所有活跃用户的遗忘曲线并保存
     * @return 结果
     */
    @Operation(summary = "计算所有活跃用户的遗忘曲线并保存")
//    @Scheduled(cron = "0 0 1 * * ?") // 每天凌晨1点执行
    @GetMapping("/updateDailyRetentionRates")
    public Result updateDailyRetentionRates() {
        Result result = new Result();
        // 获取2小时内所有活跃用户的id
        List<Integer> userIds = userService.getActiveUserIds();
        for (Integer userId : userIds) {
            List<WordReview> allWords = wordReviewService.getAllWordsForUser(userId);
            forgettingCurveService.calculateRetentionRatesAndSave(allWords);
        }
        result.success("更新成功");
        return result;
    }

    /**
     * 计算用户的遗忘曲线并保存
     * @return 结果
     */
    @Operation(summary = "计算用户的遗忘曲线并保存")
    @GetMapping("/updateByUserId")
    public Result updateByUserId() {
        Result result = new Result();
        List<WordReview> allWords = wordReviewService.getWordsByUserId();
        forgettingCurveService.calculateRetentionRatesAndSave(allWords);
        result.success("更新成功");
        return result;
    }
}

