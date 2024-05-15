package com.example.englishhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.englishhub.entity.Voiceovers;
import com.example.englishhub.entity.VoiceoversVO;
import com.example.englishhub.mapper.VoiceoversMapper;
import com.example.englishhub.service.VoiceoversService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.englishhub.utils.JwtUtil;
import com.example.englishhub.utils.Transform;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 每日一句配音表 服务实现类
 * </p>
 *
 * @author hahaha
 * @since 2024-05-11
 */

@Service
public class VoiceoversServiceImpl extends ServiceImpl<VoiceoversMapper, Voiceovers> implements VoiceoversService {

    @Autowired
    private VoiceoversMapper voiceoversMapper;

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean like(Integer voiceoverId) {
        QueryWrapper<Voiceovers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", voiceoverId);
        Voiceovers voiceovers = this.getOne(queryWrapper);
        voiceovers.setLikeCount(voiceovers.getLikeCount() + 1);
        return this.updateById(voiceovers);
    }

    @Override
    public Page<VoiceoversVO> getAllVoiceovers(Integer pageNum, Integer pageSize) {
        List<VoiceoversVO> voiceoversList = voiceoversMapper.getAllVoiceovers();
        Transform transform = new Transform();
        return transform.listToPage(voiceoversList, pageNum, pageSize);
    }

    @Override
    public Page<VoiceoversVO> getAllVoiceoversBySentenceId(Integer sentenceId, Integer pageNum, Integer pageSize) {
        List<VoiceoversVO> voiceoversList = voiceoversMapper.getAllVoiceoversBySentenceId(sentenceId);
        Transform transform = new Transform();
        return transform.listToPage(voiceoversList, pageNum, pageSize);
    }

    @Override
    public Page<VoiceoversVO> getAllVoiceoversByUserId(Integer pageNum, Integer pageSize) {
        String token = request.getHeader("token");
        String userId = JwtUtil.validateToken(token);

        List<VoiceoversVO> voiceoversList = voiceoversMapper.getAllVoiceoversByUserId(Integer.parseInt(userId));
        Transform transform = new Transform();
        return transform.listToPage(voiceoversList, pageNum, pageSize);
    }
}
