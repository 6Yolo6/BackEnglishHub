package com.example.englishhub.controller;


import com.example.englishhub.entity.Favorites;
import com.example.englishhub.service.FavoritesService;
import com.example.englishhub.utils.Result;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏表(Favorites)表控制层
 *
 * @author makejava
 * @since 2024-05-17 22:33:39
 */
@Tag(name = "收藏管理")
@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    /**
     * 服务对象
     */
    @Autowired
    private FavoritesService favoritesService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param favorites 查询实体
     * @return 所有数据
     */


    /**
     * 新增数据
     * @param favorite
     * @return
     */


    @PostMapping("/add")
    public Result addFavorite(@RequestBody Favorites favorite) {
        Result result = new Result();
        boolean isAdded = favoritesService.addFavorite(favorite);
        if (isAdded) {
            result.success("收藏成功");
        } else {
            result.fail("收藏失败");
        }
        return result;
    }

    @GetMapping("/list")
    public Result listFavorites() {
        Result result = new Result();
        List<Favorites> favorites = favoritesService.getFavoritesByUserId();
        result.setData(favorites);
        result.success("获取收藏列表成功");
        return result;
    }

    @PostMapping("/delete")
    public Result deleteFavorite(@RequestParam int resourceId, @RequestParam String resourceType) {
        Result result = new Result();
        boolean isDeleted = favoritesService.deleteFavorite(resourceId, resourceType);
        if (isDeleted) {
            result.success("删除收藏成功");
        } else {
            result.fail("删除收藏失败");
        }
        return result;
    }

}

