package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBookSeries;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 电子书系列表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-15
 */
public interface EBookSeriesService extends IService<EBookSeries> {

    List<EBookSeries> getAllSeries();

    Page<EBookSeries> getPageEBookSeries(Integer pageNum, Integer pageSize);

    boolean addSeries(EBookSeries eBookSeries);

    boolean deleteByIds(String ids);

    boolean updateUseful(Integer seriesId);
}
