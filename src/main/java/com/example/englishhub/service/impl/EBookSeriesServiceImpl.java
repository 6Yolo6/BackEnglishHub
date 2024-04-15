package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.entity.EBookSeries;
import com.example.englishhub.mapper.EBookSeriesMapper;
import com.example.englishhub.service.EBookSeriesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 电子书系列表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-15
 */
@Service
public class EBookSeriesServiceImpl extends ServiceImpl<EBookSeriesMapper, EBookSeries> implements EBookSeriesService {

    @Override
    public List<EBookSeries> getAllSeries() {
        // 只查useful为1的
        QueryWrapper<EBookSeries> wrapper = new QueryWrapper<>();
        wrapper.eq("useful", 1);
        return this.list(wrapper);
    }

    public EBookSeries getByName(String name) {
        QueryWrapper<EBookSeries> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return this.getOne(wrapper);
    }

    @Override
    public Page<EBookSeries> getPageEBookSeries(Integer pageNum, Integer pageSize) {
        QueryWrapper<EBookSeries> queryWrapper = new QueryWrapper<>();
        Page<EBookSeries> page = new Page<>(pageNum, pageSize);
        return this.page(page, queryWrapper);
    }

    @Override
    public boolean addSeries(EBookSeries eBookSeries) {
        try {
            EBookSeries isExist = getByName(eBookSeries.getName());
            if (isExist != null) {
                throw new Exception("电子书系列：" + eBookSeries.getName() + "已存在");
            } else {
                // 默认useful为1
                eBookSeries.setUseful(true);
                save(eBookSeries);
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUseful(Integer seriesId) {
        EBookSeries eBookSeries = getById(seriesId);
        eBookSeries.setUseful(!eBookSeries.getUseful());
        return updateById(eBookSeries);
    }

    @Override
    public boolean deleteByIds(String ids) {
        // 逻辑删除
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            EBookSeries eBookSeries = getById(id);
            if (eBookSeries == null) {
                throw new RuntimeException("电子书系列：" + id + "不存在");
            }
            eBookSeries.setUseful(false);
            updateById(eBookSeries);
        }
        return true;
    }
}
