package com.example.englishhub.mapper;

import com.example.englishhub.entity.Word;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 基本单词表 Mapper 接口
 * </p>
 *
 * @author hahaha
 * @since 2024-05-13
 */
@Mapper
public interface WordMapper extends BaseMapper<Word> {

    @Select("SELECT w.id, w.word, w.words_id AS wordsId, w.phonetic_uk AS phoneticUk, " +
            "w.phonetic_us AS phoneticUs, w.audio_url_uk AS audioUrlUk, w.audio_url_us AS audioUrlUs, " +
            "w.definition, w.create_time AS createTime, w.update_time AS updateTime " +
            "FROM word_relation wr JOIN word w ON wr.word_id = w.id " +
            "WHERE wr.word_book_id = #{wordBookId} " +
            "LIMIT #{offset}, #{pageSize}")
    List<Word> getPageByWordBookId(Integer wordBookId, Integer offset, Integer pageSize);


    @Select("SELECT w.id, w.word, w.phonetic_uk AS phoneticUk, w.phonetic_us AS phoneticUs, w.definition, " +
            "wd.definition AS wordsDefinition, wd.audio_url AS audioUrl, wd.video_url AS videoUrl, wd.subtext " +
            "FROM word w " +
            "LEFT JOIN words wd ON w.words_id = wd.id " +
            "WHERE w.word LIKE CONCAT('%', #{word}, '%') " +
            "LIMIT 15")
    List<WordReviewVO> searchByName(String word);

    @Select("<script>"
            + "SELECT w.id, w.word, w.phonetic_uk AS phoneticUk, w.phonetic_us AS phoneticUs, w.definition, "
            + "wd.definition AS wordsDefinition, wd.audio_url AS audioUrl, wd.video_url AS VideoUrl, wd.subtext "
            + "FROM word w "
            + "LEFT JOIN words wd ON w.words_id = wd.id "
            + "WHERE w.word IN "
            + "<foreach item='word' index='index' collection='words' open='(' separator=',' close=')'>"
            + "#{word}"
            + "</foreach>"
            + "</script>")
    List<WordReviewVO> findWordsByNames(@Param("words") List<String> words);

    WordReviewVO getDetail(Integer wordId);
}
