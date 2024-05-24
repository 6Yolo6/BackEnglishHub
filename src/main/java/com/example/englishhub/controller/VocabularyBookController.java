package com.example.englishhub.controller;


import com.example.englishhub.entity.WordReviewVO;
import com.example.englishhub.service.VocabularyBookService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 生词本表(VocabularyBook)表控制层
 *
 * @author makejava
 * @since 2024-05-17 22:30:13
 */
@Tag(name = "生词本管理")
@RestController
@RequestMapping("/vocabularyBook")
public class VocabularyBookController {
    /**
     * 服务对象
     */
    @Autowired
    private VocabularyBookService vocabularyBookService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param vocabularyBook 查询实体
     * @return 所有数据
     */


    /**
     * 根据userId查询生词本
     *
     * @return 所有数据
     */
    @Operation(summary = "根据userId查询生词本")
    @PostMapping("/getByUser")
    public Result getByUser() {
        Result result = new Result();
        List<WordReviewVO> list = vocabularyBookService.getByUser();
        result.setData(list);
        result.success("查询生词本成功");
        return result;
    }

    /**
     * 新增数据
     * @param wordId 单词id
     *
     * @return 新增结果
     */
    @Operation(summary = "新增生词本")
    @PostMapping ("/add")
    public Result add(Integer wordId) {
        Result result = new Result();
        vocabularyBookService.add(wordId);
        result.success("新增生词本成功");
        return result;
    }



    /**
     * 修改数据
     *
     * @param vocabularyBook 实体对象
     * @return 修改结果
     */


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

