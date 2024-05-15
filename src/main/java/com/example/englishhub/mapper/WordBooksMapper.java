package com.example.englishhub.mapper;

import com.example.englishhub.entity.WordBooks;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.WordBooksVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 单词库表 Mapper 接口
 * </p>
 *
 * @author hahaha
 * @since 2024-05-12
 */

@Mapper
public interface WordBooksMapper extends BaseMapper<WordBooks> {


//    learnedTodayNew: 使用 create_time = CURDATE()判断
//    reviewedToday: 使用 last_review_time = CURDATE()判断

    @Select("SELECT " +
            "wb.id, " +
            "wb.name, " +
            "wb.word_count AS totalWords, " +
            "lp.daily_new_words AS toLearnToday, " +
            "lp.daily_review_words AS toReviewToday, " +
            "lp.status AS planStatus, " +
            "DATEDIFF(lp.end_date, lp.start_date) + 1 AS totalDays, " +
            "COUNT(DISTINCT wr.id) AS learnedWords, " +
            "SUM(CASE WHEN wr.status = 4 THEN 1 ELSE 0 END) AS masteredWords, " +
            "SUM(CASE WHEN DATE(wr.create_time) = CURDATE() THEN 1 ELSE 0 END) AS learnedTodayNew, " +
            "SUM(CASE WHEN DATE(wr.last_review_time) = CURDATE() THEN 1 ELSE 0 END) AS reviewedToday, " +
            "COUNT(DISTINCT DATE(wr.create_time)) AS learnedDays " +
            "FROM word_books wb " +
            "LEFT JOIN learning_plans lp ON wb.id = lp.word_book_id " +
            "LEFT JOIN word_review wr ON wb.id = wr.word_book_id AND wr.user_id = lp.user_id " +
            "WHERE lp.user_id = #{userId} " +
            "GROUP BY wb.id, lp.id")
    WordBooksVO getByUserId(Integer userId);
}
