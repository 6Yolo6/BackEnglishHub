package com.example.englishhub.service;

import com.example.englishhub.entity.WordBookCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 单词书类别表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-15
 */
public interface WordBookCategoryService extends IService<WordBookCategory> {

    List<WordBookCategory> getAllCate();

    boolean addCate(WordBookCategory wordBookCategory);
}
