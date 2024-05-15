package com.example.englishhub.service;

import com.example.englishhub.entity.WordBooks;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.WordBooksVO;

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
}
