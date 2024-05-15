package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.englishhub.entity.WordBookCategory;
import com.example.englishhub.mapper.WordBookCategoryMapper;
import com.example.englishhub.service.WordBookCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 单词书类别表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-15
 */
@Service
public class WordBookCategoryServiceImpl extends ServiceImpl<WordBookCategoryMapper, WordBookCategory> implements WordBookCategoryService {

    @Override
    public List<WordBookCategory> getAllCate() {
        // 查询所有有效的单词书类别
        QueryWrapper<WordBookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("useful", 1);
        return list(queryWrapper);
    }

    @Override
    public boolean addCate(WordBookCategory wordBookCategory) {
        // 判断单词书类别是否已存在
        QueryWrapper<WordBookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", wordBookCategory.getName());
        WordBookCategory isExist = getOne(queryWrapper);
        if (isExist != null) {
            System.out.println("单词书类别：" + wordBookCategory.getName() + "已存在");
        } else {
            return save(wordBookCategory);
        }
        return false;
    }
}
