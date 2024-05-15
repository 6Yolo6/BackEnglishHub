package com.example.englishhub.mapper;

import com.example.englishhub.entity.Voiceovers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.VoiceoversVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 每日一句配音表 Mapper 接口
 * </p>
 *
 * @author hahaha
 * @since 2024-05-11
 */
@Mapper
public interface VoiceoversMapper extends BaseMapper<Voiceovers> {

    @Select("SELECT v.id, v.audio_url, v.user_id, u.username, u.avatar, v.sentence_id, v.like_count, v.create_time " +
            "FROM voiceovers v " +
            "JOIN user u ON v.user_id = u.id")
    List<VoiceoversVO> getAllVoiceovers();

    @Select("SELECT v.id, v.audio_url, v.user_id, u.username, u.avatar, v.sentence_id, v.like_count, v.create_time " +
            "FROM voiceovers v " +
            "JOIN user u ON v.user_id = u.id " +
            "WHERE v.sentence_id = #{sentenceId}")
    List<VoiceoversVO> getAllVoiceoversBySentenceId(Integer sentenceId);

    @Select("SELECT v.id, v.audio_url, v.user_id, u.username, u.avatar, v.sentence_id, v.like_count, v.create_time " +
            "FROM voiceovers v " +
            "JOIN user u ON v.user_id = u.id " +
            "WHERE v.user_id = #{userId}")
    List<VoiceoversVO> getAllVoiceoversByUserId(int userId);
}
