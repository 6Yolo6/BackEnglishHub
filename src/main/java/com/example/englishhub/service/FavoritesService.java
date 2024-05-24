package com.example.englishhub.service;

import com.example.englishhub.entity.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收藏表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
public interface FavoritesService extends IService<Favorites> {

    boolean addFavorite(Favorites favorite);

    List<Favorites> getFavoritesByUserId();

    boolean deleteFavorite(int resourceId, String resourceType);
}
