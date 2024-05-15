package com.example.englishhub.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.WordBookCategory;
import com.example.englishhub.service.WordBookCategoryService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 单词书类别表(WordBookCategory)表控制层
 *
 * @author makejava
 * @since 2024-05-15 14:51:42
 */

@Tag(name = "单词书类别管理")
@RestController
@RequestMapping("/wordBookCategory")
public class WordBookCategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private WordBookCategoryService wordBookCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有单词书类别")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<WordBookCategory> wordBookCategoryPage = wordBookCategoryService.page(new Page<>(pageNum, pageSize));
        result.setData(wordBookCategoryPage);
        result.success("查询所有单词书类别成功");
        return result;
    }


    /**
     * 查询所有有效数据
     *
     * @return 所有数据
     */
    @Operation(summary = "查询所有有效数据")
    @GetMapping("/getAll")
    public Result getAll() {
        Result result = new Result();
        result.setData(wordBookCategoryService.getAllCate());
        result.success("查询所有有效数据成功");
        return result;
    }

    /**
     * 新增数据
     *
     * @param wordBookCategory 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody WordBookCategory wordBookCategory) {
        Result result = new Result();
        if (wordBookCategoryService.addCate(wordBookCategory)) {
            result.success("新增数据成功");
        } else {
            result.fail("新增数据失败");
        }
        return result;
    }

    /**
     * 修改数据
     *
     * @param wordBookCategory 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody WordBookCategory wordBookCategory) {
        Result result = new Result();
        if (wordBookCategoryService.updateById(wordBookCategory)) {
            result.success("修改数据成功");
        } else {
            result.fail("修改数据失败");
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

