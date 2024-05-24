package com.example.englishhub.mapper;

import com.example.englishhub.entity.VocabularyBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.WordReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 生词本表 Mapper 接口
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
@Mapper
public interface VocabularyBookMapper extends BaseMapper<VocabularyBook> {
        @Select("""
        SELECT 
            w.id, 
            w.word, 
            w.phonetic_uk AS phoneticUk, 
            w.phonetic_us AS phoneticUs, 
            w.definition, 
            wd.definition AS wordsDefinition, 
            wd.audio_url AS audioUrl, 
            wd.video_url AS videoUrl, 
            wd.subtext 
        FROM 
            vocabulary_book vb
        JOIN 
            word w ON vb.word_id = w.id
        LEFT JOIN 
            words wd ON w.words_id = wd.id
        WHERE 
            vb.user_id = #{userId}
        """)
        List<WordReviewVO> getByUser(@Param("userId") int userId);
}
