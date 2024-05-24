package com.example.englishhub.service;

import com.example.englishhub.entity.VocabularyBook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.WordReviewVO;

import java.util.List;

/**
 * <p>
 * 生词本表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
public interface VocabularyBookService extends IService<VocabularyBook> {

    VocabularyBook add(Integer wordId);


    List<WordReviewVO> getByUser();
}
