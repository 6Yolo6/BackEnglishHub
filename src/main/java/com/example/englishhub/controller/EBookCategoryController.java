package com.example.englishhub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBookCategory;
import com.example.englishhub.service.EBookCategoryService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * (EBookCategory)表控制层
 *
 * @author makejava
 * @since 2024-04-25 13:55:13
 */

@RestController
@RequestMapping("/eBookCategory")
@Tag(name = "电子书分类管理")
public class EBookCategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private EBookCategoryService eBookCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<EBookCategory> page = eBookCategoryService.page(new Page<>(pageNum, pageSize));
        result.setData(page);
        result.success("分页查询所有数据成功");
        return result;
    }



    /**
     * 查询所有有效数据
     * @return 所有数据
     */
    @Operation(summary = "查询所有有效数据")
    @GetMapping("/getAll")
    public Result getAll() {
        Result result = new Result();
        result.setData(eBookCategoryService.getAllCate());
        result.success("查询所有有效数据成功");
        return result;
    }


    /**
     * 新增数据
     * @body eBookCategory 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增数据")
    @PostMapping("/add")
    public Result add(@RequestBody EBookCategory eBookCategory) {
        Result result = new Result();
        if (eBookCategoryService.addCategory(eBookCategory)) {
            result.success("新增数据成功");
        } else {
            result.fail("新增数据失败");
        }
        return result;
    }



    /**
     * 修改数据
     *
     * @param eBookCategory 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据")
    @PostMapping("/update")
    public Result update(@RequestBody EBookCategory eBookCategory) {
        Result result = new Result();
        if (eBookCategoryService.updateById(eBookCategory)) {
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

