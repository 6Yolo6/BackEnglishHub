package com.example.englishhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.WordReview;
import com.example.englishhub.entity.WordReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 单词复习表 Mapper 接口
 * </p>
 * @Author: hahaha
 * @Date: 2024/4/8 15:14
 */

@Mapper
public interface WordReviewMapper extends BaseMapper<WordReview> {
//    @Select({
//            "<script>",
//            "SELECT * FROM (",
//            "  (SELECT",
//            "      w.id, w.word, w.phonetic_uk, w.phonetic_us, w.definition,",
//            "      wd.audio_url, wd.video_url, wd.definition AS definitionDetail, wd.subtext, wr.status AS reviewStatus",
//            "   FROM word_review wr",
//            "   JOIN word w ON wr.word_id = w.id",
//            "   JOIN words wd ON w.words_id = wd.id",
//            "   WHERE wr.user_id = #{userId} AND wr.word_book_id = #{wordBookId}",
//            "     AND wr.next_review_time <= CURRENT_TIMESTAMP",
//            "   ORDER BY wr.next_review_time ASC",
//            "   LIMIT #{dailyReviewWords})",
//            "UNION ALL",
//            "  (SELECT",
//            "      w.id, w.word, w.phonetic_uk, w.phonetic_us, w.definition,",
//            "      wd.audio_url, wd.video_url, wd.definition AS definitionDetail, wd.subtext, 0 AS reviewStatus",
//            "   FROM word_relation wr",
//            "   JOIN word w ON wr.word_id = w.id",
//            "   JOIN words wd ON w.words_id = wd.id",
//            "   LEFT JOIN word_review wrv ON wrv.word_id = w.id AND wrv.user_id = #{userId}",
//            "   WHERE wr.word_book_id = #{wordBookId} AND wrv.id IS NULL",
//            "   ORDER BY w.id ASC",
//            "   LIMIT #{dailyNewWords})",
//            ") AS combined_results",
//            "</script>"
//    })
//    List<WordReviewVO> getWordsToday(Integer userId,
//                                     Integer wordBookId,
//                                     Integer dailyNewWords,
//                                     Integer dailyReviewWords);


    @Select("SELECT w.id, w.word, w.phonetic_uk, w.phonetic_us, w.definition," +
            " wd.audio_url, wd.video_url, wd.definition AS definitionDetail, wd.subtext, 0 AS reviewStatus" +
            " FROM word_relation wr" +
            " JOIN word w ON wr.word_id = w.id" +
            " JOIN words wd ON w.words_id = wd.id" +
            " LEFT JOIN word_review wrv ON wrv.word_id = w.id AND wrv.user_id = #{userId}" +
            " WHERE wr.word_book_id = #{wordBookId} AND wrv.id IS NULL" +
            " ORDER BY w.id ASC" +
            " LIMIT #{dailyNewWords}")
    List<WordReviewVO> getNewWordsToday(Integer userId, Integer wordBookId, Integer dailyNewWords);

    @Select("SELECT w.id, w.word, w.phonetic_uk, w.phonetic_us, w.definition," +
            " wd.audio_url, wd.video_url, wd.definition AS definitionDetail, wd.subtext, wr.status AS reviewStatus" +
            " FROM word_review wr" +
            " JOIN word w ON wr.word_id = w.id" +
            " JOIN words wd ON w.words_id = wd.id" +
            " WHERE wr.user_id = #{userId} AND wr.word_book_id = #{wordBookId}" +
            " AND wr.next_review_time <= CURRENT_TIMESTAMP" +
            " ORDER BY wr.next_review_time ASC" +
            " LIMIT #{dailyReviewWords}")
    List<WordReviewVO> getReviewWordsToday(Integer userId, Integer wordBookId, Integer dailyReviewWords);
}
