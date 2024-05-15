package com.example.englishhub.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBookSeries;
import com.example.englishhub.service.EBookSeriesService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 电子书系列表(EBookSeries)表控制层
 *
 * @author makejava
 * @since 2024-04-15 15:59:29
 */

@RestController
@RequestMapping("/eBookSeries")
@Tag(name = "电子书系列管理")
public class EBookSeriesController {
    /**
     * 服务对象
     */
    @Autowired
    private EBookSeriesService eBookSeriesService;


    /**
     * 查询所有有效数据
     * @return 所有数据
     */
    @Operation(summary = "查询所有有效电子书系列")
    @GetMapping("/getAll")
    public Result getAll() {
        Result result = new Result();
        List<EBookSeries> eBookSeriesList = eBookSeriesService.getAllSeries();
        result.setData(eBookSeriesList);
        result.success("查询所有有效电子书系列成功");
        return result;
    }


    /**
     * 分页查询所有数据
     * @params pageNum  页码
     * @params pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有电子书系列")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<EBookSeries> eBookSeriesPage = eBookSeriesService.page(new Page<>(pageNum, pageSize));
        result.setData(eBookSeriesPage);
        result.success("查询所有电子书系列成功");
        return result;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过ID查询电子书系列")
    @GetMapping("/getById")
    public Result getById(Integer id) {
        Result result = new Result();
        EBookSeries eBookSeries = eBookSeriesService.getById(id);
        result.setData(eBookSeries);
        result.success("查询电子书系列成功");
        return result;
    }


    /**
     * 新增数据
     *
     * @param eBookSeries 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增电子书系列")
    @PostMapping("/add")
    public Result add(@RequestBody EBookSeries eBookSeries) {
        Result result = new Result();
        if (eBookSeriesService.addSeries(eBookSeries)) {
            result.success("新增电子书系列成功");
        } else {
            result.fail("新增电子书系列失败");
        }
        return result;
    }


    /**
     * 修改数据
     *
     * @param eBookSeries 实体对象
     * @return 修改结果
     */
    @Operation(summary = "更新电子书系列信息")
    @PostMapping("/update")
    public Result update(@RequestBody EBookSeries eBookSeries) {
        Result result = new Result();
        if (eBookSeriesService.updateById(eBookSeries)) {
            result.setData(eBookSeries);
            result.success("编辑电子书系列成功");
        } else {
            result.fail("编辑电子书系列失败");
        }
        return result;
    }



    /**
     * 更新useful
     *
     * @param seriesId 主键
     */
    @Operation(summary = "更新电子书系列状态")
    @PostMapping("/updateUseful")
    public Result updateUseful(Integer seriesId) {
        Result result = new Result();
        if (eBookSeriesService.updateUseful(seriesId)) {
            result.success("更新电子书系列状态成功");
        } else {
            result.fail("更新电子书系列状态失败");
        }
        return result;
    }

    /**
     * 批量禁用电子书系列
     *
     * @param ids 主键ids逗号分隔
     * @return 删除结果
     */
    @Operation(summary = "禁用电子书系列")
    @PostMapping("/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        if (eBookSeriesService.deleteByIds(ids)) {
            result.success("禁用电子书系列成功");
        } else {
            result.fail("禁用电子书系列失败");
        }
        return result;
    }

}

