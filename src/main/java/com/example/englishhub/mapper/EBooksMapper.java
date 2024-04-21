package com.example.englishhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.EBookVO;
import com.example.englishhub.entity.EBooks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: hahaha
 * @Date: 2024/4/14 21:35
 */

@Mapper
public interface EBooksMapper extends BaseMapper<EBooks> {

    @Select("SELECT eb.*, ebs.name as seriesName, ebs.id as seriesId " +
            "FROM e_books eb " +
            "LEFT JOIN e_book_series ebs ON eb.series_id = ebs.id")
    List<EBookVO> getAllEBooks();
}
