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
     * @param learningPlans 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody LearningPlans learningPlans) {
        Result result = new Result();
        if (learningPlansService.addPlan(learningPlans)) {
            result.success("新增成功");
        } else {
            result.fail("新增失败");
        }
        return result;
    }



    /**
     * 修改数据
     *
     * @param learningPlans 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody LearningPlans learningPlans) {
        Result result = new Result();
        if (learningPlansService.updateById(learningPlans)) {
            result.success("修改成功");
        } else {
            result.fail("修改失败");
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

