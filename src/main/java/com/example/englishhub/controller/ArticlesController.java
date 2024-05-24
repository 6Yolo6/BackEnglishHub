package com.example.englishhub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Articles;
import com.example.englishhub.service.ArticlesService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * 文章资源表(Articles)表控制层
 *
 * @author hahaha
 * @since 2024-04-11 16:10:53
 */

@RestController
@RequestMapping("/articles")
@Tag(name = "文章资源管理")
public class ArticlesController {
    /**
     * 服务对象
     */
    @Autowired
    private ArticlesService articlesService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @GetMapping("/getPage")
    @Operation(summary = "分页查询所有文章资源")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<Articles> articlesPage = articlesService.page(new Page<>(pageNum, pageSize));
        result.setData(articlesPage);
        result.success("查询所有文章资源成功");
        return result;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/getById")
    @Operation(summary = "通过id查询文章资源")
    public Result getById(Integer id) {
        Result result = new Result();
        Articles articles = articlesService.getById(id);
        result.setData(articles);
        result.success("通过id查询文章资源成功");
        return result;
    }


    /**
     * 新增数据
     *
     * @param articles 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    @Operation(summary = "新增文章资源")
    public Result add(@RequestBody Articles articles) {
        Result result = new Result();
        if (articlesService.save(articles)) {
            result.success("新增文章资源成功");
        } else {
            result.fail("新增文章资源失败");
        }
        return result;
    }


    /**
     * 修改数据
     *
     * @param articles 实体对象
     * @return 修改结果
     */
    @PostMapping("/update")
    @Operation(summary = "更新文章资源信息")
    public Result update(@RequestBody Articles articles) {
        Result result = new Result();
        if (articlesService.updateById(articles)) {
            result.success("更新文章资源信息成功");
        } else {
            result.fail("更新文章资源信息失败");
        }
        return result;
    }


    /**
     * 获取最新条数据
     *
     * @return 返回结果
     */
    @GetMapping("/getTop")
    @Operation(summary = "获取最新条文章资源")
    public Result getTop(Integer top) {
        Result result = new Result();
        List<Articles> articlesList = articlesService.list(new QueryWrapper<Articles>().orderByDesc("publish_time").last("limit " + top));
        result.setData(articlesList);
        result.success("获取最新3条文章资源成功");
        return result;
    }
}

