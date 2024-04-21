package com.example.englishhub.controller;


import com.example.englishhub.entity.ReadingProgress;
import com.example.englishhub.service.ReadingProgressService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 保存用户阅读进度(ReadingProgress)表控制层
 *
 * @author hahaha
 * @since 2024-04-17 23:39:38
 */
@RestController
@RequestMapping("/readingProgress")
public class ReadingProgressController{
    /**
     * 服务对象
     */
    @Autowired
    private ReadingProgressService readingProgressService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @body readingProgress 查询实体
     * @return 所有数据
     */


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */

    /**
     * 查询用户阅读进度
     * @param eBookId
     * @return
     */
    @Operation(summary = "查询用户阅读进度")
    @GetMapping("/getProgress")
    public Result getProgress(Integer eBookId) {
        Result result = new Result();
        ReadingProgress progress = readingProgressService.getProgress(eBookId);
        result.setData(progress);
        result.success("查询用户阅读进度成功");
        return result;
    }

    /**
     * 新增数据
     *
     * @body readingProgress 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result addProgress(@RequestBody ReadingProgress progress) {
        Result result = new Result();
        if (readingProgressService.addProgress(progress)) {
            result.success("新增用户阅读进度成功");
        } else {
            result.fail("新增用户阅读进度失败");
        }
        return result;
    }

    /**
     * 修改数据
     *
     * @body readingProgress 实体对象
     * @return 修改结果
     */
    @Operation(summary = "更新用户阅读进度")
    @PostMapping("/update")
    public Result update(@RequestBody ReadingProgress readingProgress) {
        Result result = new Result();
        if (readingProgressService.updateById(readingProgress)) {
            result.success("更新用户阅读进度成功");
        } else {
            result.fail("更新用户阅读进度失败");
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

