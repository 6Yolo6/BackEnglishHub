package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.EBookCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-25
 */
public interface EBookCategoryService extends IService<EBookCategory> {

    boolean addCategory(EBookCategory eBookCategory);

    List<EBookCategory> getAllCate();
}
