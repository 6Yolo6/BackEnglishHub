package com.example.englishhub.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.DailySentences;
import com.example.englishhub.service.DailySentencesService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 每日一句表(DailySentences)表控制层
 *
 * @author makejava
 * @since 2024-05-10 22:43:22
 */


@RestController
@RequestMapping("/dailySentences")
@Tag(name = "每日一句管理")
public class DailySentencesController {
    /**
     * 服务对象
     */
    @Autowired
    private DailySentencesService dailySentencesService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @GetMapping("/getPage")
    @Operation(summary = "分页查询所有每日一句")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<DailySentences> dailySentencesPage = dailySentencesService.page(new Page<>(pageNum, pageSize));
        result.setData(dailySentencesPage);
        result.success("查询所有每日一句成功");
        return result;
    }

    /**
     * 通过日期单条数据
     *
     * @param date 日期
     * @return 单条数据
     */
    @GetMapping("/getByDate")
    @Operation(summary = "通过日期查询每日一句")
    public Result getByDate(String date) {
        Result result = new Result();
        DailySentences dailySentence = dailySentencesService.getByDate(date);
        result.setData(dailySentence);
        result.success("查询每日一句成功");
        return result;
    }

    /**
     * 新增数据
     *
     * @param dailySentences 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param dailySentences 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

