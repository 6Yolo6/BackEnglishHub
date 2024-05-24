package com.example.englishhub.service;

import com.example.englishhub.entity.Videos;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 视频资源表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-16
 */
public interface VideosService extends IService<Videos> {

    List<Videos> getByCategoryId(Integer categoryId);
}
