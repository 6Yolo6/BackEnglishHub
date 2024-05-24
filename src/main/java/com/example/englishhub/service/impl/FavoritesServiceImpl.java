package com.example.englishhub.service.impl;

import com.example.englishhub.entity.Favorites;
import com.example.englishhub.mapper.FavoritesMapper;
import com.example.englishhub.service.FavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements FavoritesService {

    @Override
    public boolean addFavorite(Favorites favorite) {
        return false;
    }

    @Override
    public List<Favorites> getFavoritesByUserId() {
        return null;
    }

    @Override
    public boolean deleteFavorite(int resourceId, String resourceType) {
        return false;
    }
}
