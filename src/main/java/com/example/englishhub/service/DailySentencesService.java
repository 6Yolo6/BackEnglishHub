package com.example.englishhub.service;

import com.example.englishhub.entity.DailySentences;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 每日一句表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-10
 */
public interface DailySentencesService extends IService<DailySentences> {

    DailySentences getByDate(String date);
}
