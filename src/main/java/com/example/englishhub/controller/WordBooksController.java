package com.example.englishhub.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.WordBooks;
import com.example.englishhub.entity.WordBooksVO;
import com.example.englishhub.service.WordBooksService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单词书表(WordBooks)表控制层
 *
 * @author makejava
 * @since 2024-05-12 22:07:44
 */

@Tag(name = "单词书管理")
@RestController
@RequestMapping("/wordBooks")
public class WordBooksController {
    /**
     * 服务对象
     */
    @Autowired
    private WordBooksService wordBooksService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有单词书")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<WordBooks> wordBooksPage = wordBooksService.page(new Page<>(pageNum, pageSize));
        result.setData(wordBooksPage);
        result.success("查询所有单词书成功");
        return result;
    }


    /**
     * 根据userId查询单条单词书数据
     *
     * @return
     */
    @Operation(summary = "根据userId查询单条单词书数据")
    @GetMapping("/getByUserId")
    public Result getByUserId() {
        Result result = new Result();
        WordBooksVO wordBooks = wordBooksService.getByUserId();
        result.setData(wordBooks);
        result.success("根据userId查询单条单词书数据成功");
        return result;
    }


    /**
     * 新增数据
     *
     * @param wordBooks 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param wordBooks 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

