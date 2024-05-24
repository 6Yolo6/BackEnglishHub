package com.example.englishhub.controller;


import com.example.englishhub.entity.LearningPlans;
import com.example.englishhub.service.LearningPlansService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学习计划表(LearningPlans)表控制层
 *
 * @author hahaha
 * @since 2024-04-19 18:09:10
 */

@RestController
@RequestMapping("/learningPlans")
@Tag(name = "学习计划管理")
public class LearningPlansController{
    /**
     * 服务对象
     */
    @Autowired
    private LearningPlansService learningPlansService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param learningPlans 查询实体
     * @return 所有数据
     */


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */


    /**
     * 新增数据
     *
     * @param worBookId 单词书id
     * @param dailyNewWords 每日新词数
     * @param dailyReviewWords 每日复习数
     * @param endDate 计划结束日期
     * @return 新增结果
     */
//    @Operation(summary = "新增数据")
//    @PostMapping("/add")
//    public Result add(Integer worBookId, Integer dailyNewWords, Integer dailyReviewWords, String endDate) {
//        Result result = new Result();
//        if (learningPlansService.addPlan(worBookId, dailyNewWords, dailyReviewWords, endDate)) {
//            result.success("新增成功");
//        } else {
//            result.fail("新增失败");
//        }
//        return result;
//    }



    /**
     * 新增或修改数据
     *
     * @param worBookId 单词书id
     * @param dailyNewWords 每日新词数
     * @param dailyReviewWords 每日复习数
     * @param endDate 计划结束日期
     * @return 修改结果
     */
    @Operation(summary = "新增或修改数据")
@PostMapping("/saveOrUpdate")
public Result saveOrUpdate(Integer worBookId, Integer dailyNewWords, Integer dailyReviewWords, String endDate) {
    Result result = new Result();
    if (learningPlansService.upsertPlan(worBookId, dailyNewWords, dailyReviewWords, endDate)) {
        result.success("操作成功");
    } else {
        result.fail("操作失败");
    }
    return result;
}


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

