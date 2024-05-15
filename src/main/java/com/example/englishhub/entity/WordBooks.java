package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * <p>
 * 单词库表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-12
 */

@Data
@Tag(name = "WordBooks", description = "单词库表")
@TableName("word_books")
public class WordBooks extends BaseEntity{
    /**
     * 单词书名称
     */
    private String name;

    /**
     * 关联word_book_category表，单词书所属单词库类别
     */
    private Integer categoryId;

    /**
     * 创建时间
     */
    @CreationTimestamp
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @UpdateTimestamp
    private Timestamp updateTime;

    /**
     * 单词总数
     */
    private Integer wordCount;
}
