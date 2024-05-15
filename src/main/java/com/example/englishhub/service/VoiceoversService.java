package com.example.englishhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Voiceovers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.englishhub.entity.VoiceoversVO;

/**
 * <p>
 * 每日一句配音表 服务类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-11
 */
public interface VoiceoversService extends IService<Voiceovers> {

    boolean like(Integer voiceoverId);

    Page<VoiceoversVO> getAllVoiceovers(Integer pageNum, Integer pageSize);

    Page<VoiceoversVO> getAllVoiceoversBySentenceId(Integer sentenceId, Integer pageNum, Integer pageSize);

    Page<VoiceoversVO> getAllVoiceoversByUserId(Integer pageNum, Integer pageSize);
}
