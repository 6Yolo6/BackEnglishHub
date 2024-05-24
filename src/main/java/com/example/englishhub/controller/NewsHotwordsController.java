package com.example.englishhub.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.NewsHotwords;
import com.example.englishhub.service.NewsHotwordsService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新闻热词表(NewsHotwords)表控制层
 *
 * @author makejava
 * @since 2024-05-15 21:50:59
 */
@Tag(name = "新闻热词管理")
@RestController
@RequestMapping("/newsHotwords")
public class NewsHotwordsController {
    /**
     * 服务对象
     */
    @Autowired
    private NewsHotwordsService newsHotwordsService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有新闻热词")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<NewsHotwords> newsHotwordsPage = newsHotwordsService.page(new Page<>(pageNum, pageSize));
        result.setData(newsHotwordsPage);
        result.success("查询所有新闻热词成功");
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
     * @param newsHotwords 实体对象
     * @return 新增结果
     */



    /**
     * 修改数据
     *
     * @param newsHotwords 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

