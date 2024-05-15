package com.example.englishhub.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Word;
import com.example.englishhub.service.WordService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 基本单词表(Word)表控制层
 *
 * @author makejava
 * @since 2024-05-13 22:46:18
 */
@Tag(name = "基本单词管理")
@RestController
@RequestMapping("/word")
public class WordController {
    /**
     * 服务对象
     */
    @Autowired
    private WordService wordService;

    /**
     * 根据wordBookId分页查询所有数据
     *
     * @param wordBookId 单词书ID
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @GetMapping("/getPageByWordBookId")
    public Result getPageByWordBookId(Integer wordBookId, Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<Word> wordPage = wordService.getPageByWordBookId(wordBookId, pageNum, pageSize);
        result.setData(wordPage);
        result.success("查询所有单词成功");
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
     * @param word 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param word 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

