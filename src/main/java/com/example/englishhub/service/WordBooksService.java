package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.WordBooks;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.WordBooksVO;

import java.util.List;

/**
 * <p>
 * 单词库表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-12
 */
public interface WordBooksService extends IService<WordBooks> {

    WordBooksVO getByUserId();

    Page<WordBooks> getByCategoryId(Integer categoryId, Integer pageNum, Integer pageSize);

    List<WordBooks> getByCate(Integer categoryId);
}
