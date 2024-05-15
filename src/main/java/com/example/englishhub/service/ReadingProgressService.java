package com.example.englishhub.service;

import com.example.englishhub.entity.ReadingProgress;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 保存用户阅读进度 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-04-17
 */

public interface ReadingProgressService extends IService<ReadingProgress> {

    ReadingProgress getProgress(Integer eBookId);

    boolean saveOrUpdateProgress(Integer eBookId, String progress);
}
