package com.example.englishhub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Articles;
import com.example.englishhub.service.ArticlesService;
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
@RequestMapping("articles")
public class ArticlesController {
    /**
     * 服务对象
     */
    @Autowired
    private ArticlesService articlesService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param articles 查询实体
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
     * @param articles 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param articles 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

