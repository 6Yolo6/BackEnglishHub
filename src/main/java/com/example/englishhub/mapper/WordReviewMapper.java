package com.example.englishhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.WordReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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
    @Select("SELECT * FROM WordReview WHERE nextReviewTime <= #{currentTime} AND userId = #{userId}")
    List<WordReview> getAllReviewsForToday(LocalDateTime currentTime, int userId);
}
