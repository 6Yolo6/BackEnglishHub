package com.example.englishhub.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBooksVO;
import com.example.englishhub.entity.EBooks;
import com.example.englishhub.service.EBooksService;
import com.example.englishhub.utils.Result;
import com.example.englishhub.utils.Upload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 电子书表(EBooks)表控制层
 *
 * @author makejava
 * @since 2024-04-14 20:51:34
 */

@RestController
@RequestMapping("/eBooks")
@Tag(name = "电子书管理")
public class EBooksController {
    /**
     * 服务对象
     */
    @Autowired
    private EBooksService eBooksService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有电子书")
    @GetMapping("/getPage")
    public Result getPage(Integer pageNum, Integer pageSize) {
        Result result = new Result();
        Page<EBooksVO> eBooksPage = eBooksService.getAllEBooks(pageNum, pageSize);
        result.setData(eBooksPage);
        result.success("查询所有电子书成功");
        return result;
    }

    /**
     * 通过seriesId查询电子书列表
     *
     * @param seriesId 系列id
     * @return 所有数据
     */
    @Operation(summary = "通过seriesId查询电子书列表")
    @GetMapping("/getBySeriesId")
    public Result getBySeriesId(Integer seriesId) {
        Result result = new Result();
        List<EBooks> eBooksList = eBooksService.list(new QueryWrapper<EBooks>().eq("series_id", seriesId));
        result.setData(eBooksList);
        result.success("通过seriesId查询电子书列表成功");
        return result;
    }


    /**
     * 获取最新5条数据
     *
     * @return 返回数据
     */
    @Operation(summary = "获取最新5条电子书")
    @GetMapping("/getTop5")
    public Result getTop5() {
        Result result = new Result();
        List<EBooks> eBooksList = eBooksService.list(new QueryWrapper<EBooks>().orderByDesc("create_time").last("limit 5"));
        result.setData(eBooksList);
        result.success("获取最新5条电子书成功");
        return result;
    }


    /**
     * 新增数据
     *
     * @Body eBooks 实体对象
     * @return 新增结果
     */
    @Operation(summary = "新增电子书")
    @PostMapping("/add")
    public Result add(@RequestBody EBooks eBooks) {
        Result result = new Result();
        if (eBooksService.addEBook(eBooks)) {
            result.success("新增电子书成功");
        } else {
            result.fail("新增电子书失败");
        }
        return result;
    }



    /**
     * 更新数据
     *
     * @Body eBooks 实体对象
     * @return 修改结果
     */
    @Operation(summary = "更新电子书信息")
    @PostMapping("/update")
    public Result update(@RequestBody EBooks eBooks) {
        System.out.println("eBooks = " + eBooks);

        System.out.println("eBooks。title " + eBooks.getTitle());
        Result result = new Result();
        if (eBooksService.updateById(eBooks)) {
            result.success("更新电子书信息成功");
        } else {
            result.fail("更新电子书信息失败");
        }
        return result;
    }


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */


    /**
     * 上传电子书
     *
     * @param file 电子书文件
     * @return 上传结果
     */
    @Operation(summary = "上传电子书")
    @PostMapping("/uploadEBook")
    public Result uploadEBook(@RequestParam(value = "file") MultipartFile file,
                              @RequestParam(value = "id", required = false) Integer id) {
        System.out.println("file = " + file);
        Result result = new Result();
        Upload fileUpload=new Upload();
        // 上传文件到阿里云oss，返回文件url
        String url= fileUpload.upload(file);
        if (url != null) {
            if (id != null) {
                // 更新操作
                EBooks existingEBook = eBooksService.getById(id);
                if (existingEBook != null) {
                    // 删除原来的文件
                    fileUpload.delete(existingEBook.getFilePath());
                } else {
                    result.fail("电子书不存在");
                }
            }
            result.success("上传电子书成功");
            result.setData(url);
        } else {
            result.fail("上传电子书失败");
        }
        return result;
    }
}

