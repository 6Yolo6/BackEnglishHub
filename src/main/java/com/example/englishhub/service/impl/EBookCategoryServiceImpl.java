package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBookCategory;
import com.example.englishhub.mapper.EBookCategoryMapper;
import com.example.englishhub.service.EBookCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-25
 */
@Service
public class EBookCategoryServiceImpl extends ServiceImpl<EBookCategoryMapper, EBookCategory> implements EBookCategoryService {

    @Override
    public boolean addCategory(EBookCategory eBookCategory) {
        EBookCategory isExist = getByName(eBookCategory.getName());
        if (isExist != null) {
            System.out.println("电子书类别：" + eBookCategory.getName() + "已存在");
        } else {
            return this.save(eBookCategory);
        }
        return false;
    }

    @Override
    public List<EBookCategory> getAllCate() {
        // 只查useful为1的
        QueryWrapper<EBookCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("useful", 1);
        return this.list(wrapper);

    }

    public EBookCategory getByName(String name) {
        QueryWrapper<EBookCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return this.getOne(wrapper);
    }
}
