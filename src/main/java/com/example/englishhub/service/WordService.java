package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Word;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 基本单词表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-13
 */
public interface WordService extends IService<Word> {

    Page<Word> getPageByWordBookId(Integer wordBookId, Integer pageNum, Integer pageSize);
}
