package com.example.englishhub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Videos;
import com.example.englishhub.service.VideosService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 视频资源表(Videos)表控制层
 *
 * @author makejava
 * @since 2024-05-16 10:34:44
 */
@Tag(name = "视频资源管理")
@RestController
@RequestMapping("/videos")
public class VideosController {
    /**
     * 服务对象
     */
    @Autowired
    private VideosService videosService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有视频资源")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<Videos> videos = videosService.page(new Page<>(pageNum, pageSize));
        result.setData(videos);
        result.success("查询所有视频资源成功");
        return result;
    }


    /**
     * 通过categoryId查询数据
     *
     * @param categoryId 类别id
     * @return 所有数据
     */
    @Operation(summary = "通过categoryId查询视频资源")
    @GetMapping("/getByCategoryId")
    public Result getByCategoryId(Integer categoryId) {
        Result result = new Result();
        List<Videos> videos = videosService.getByCategoryId(categoryId);
        result.setData(videos);
        result.success("通过categoryId查询视频资源成功");
        return result;
    }


    /**
     * 新增数据
     *
     * @param videos 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param videos 实体对象
     * @return 修改结果
     */

    /**
     * 获取最新5条数据
     *
     * @return 返回数据
     */
    @Operation(summary = "获取最新5条视频资源")
    @GetMapping("/getTop5")
    public Result getTop5() {
        Result result = new Result();
        List<Videos> videos = videosService.list(new QueryWrapper<Videos>().orderByDesc("create_time").last("limit 5"));
        result.setData(videos);
        result.success("获取最新5条视频资源成功");
        return result;
    }

}

