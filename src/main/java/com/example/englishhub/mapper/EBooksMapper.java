package com.example.englishhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.englishhub.entity.EBooksVO;
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

//    @Select("SELECT eb.*, ebs.name as seriesName, ebs.id as seriesId " +
//            "FROM e_books eb " +
//            "LEFT JOIN e_book_series ebs ON eb.series_id = ebs.id")
//    List<EBookVO> getAllEBooks();

    // 多表查询所有电子书，包括电子书系列和电子书分类
    @Select("SELECT eb.*, ebs.name as seriesName, ebs.id as seriesId, ebc.name as categoryName, ebc.id as categoryId " +
            "FROM e_books eb " +
            "LEFT JOIN e_book_series ebs ON eb.series_id = ebs.id " +
            "LEFT JOIN e_book_category ebc ON ebs.category_id = ebc.id")
    List<EBooksVO> getAllEBooks();
}
