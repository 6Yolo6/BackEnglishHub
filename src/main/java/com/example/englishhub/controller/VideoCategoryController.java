package com.example.englishhub.controller;


import com.example.englishhub.service.VideoCategoryService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (VideoCategory)表控制层
 *
 * @author makejava
 * @since 2024-05-16 11:55:01
 */
@Tag(name = "视频类别管理")
@RestController
@RequestMapping("/videoCategory")
public class VideoCategoryController{
    /**
     * 服务对象
     */
    @Autowired
    private VideoCategoryService videoCategoryService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param videoCategory 查询实体
     * @return 所有数据
     */


    /**
     * 查询所有数据
     *
     * @return 所有数据
     */
    @Operation(summary = "查询所有视频类别")
    @GetMapping("/getAll")
    public Result getAll() {
        Result result = new Result();
        result.setData(videoCategoryService.list());
        result.success("查询所有视频类别成功");
        return result;
    }

    /**
     * 新增数据
     *
     * @param videoCategory 实体对象
     * @return 新增结果
     */


    /**
     * 修改数据
     *
     * @param videoCategory 实体对象
     * @return 修改结果
     */

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */

}

