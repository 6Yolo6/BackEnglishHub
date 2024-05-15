package com.example.englishhub.entity;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @Author: hahaha
 * @Date: 2024/4/15 16:34
 */
@Data
@ToString
public class EBooksVO {
    private Integer id;
    private String title;
    private String author;
    private String filePath;
    private String fileType;
    private Integer seriesId;   // 来自e_book_series表
    private String seriesName; // 来自e_book_series表
    private Integer categoryId; // 来自e_book_category表
    private String categoryName; // 来自e_book_category表

    private Timestamp createTime;
    private Timestamp updateTime;
}
