package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.englishhub.entity.Videos;
import com.example.englishhub.mapper.VideosMapper;
import com.example.englishhub.service.VideosService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 视频资源表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-16
 */
@Service
public class VideosServiceImpl extends ServiceImpl<VideosMapper, Videos> implements VideosService {

    @Override
    public List<Videos> getByCategoryId(Integer categoryId) {
        QueryWrapper<Videos> wrapper = new QueryWrapper<>();
        wrapper.eq("category", categoryId);
        return this.list(wrapper);
    }
}
