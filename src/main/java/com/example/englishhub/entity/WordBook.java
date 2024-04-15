package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * @Author: hahaha
 * @Date: 2024/4/8 14:24
 */

/**
 * 单词书实体类
 */
@Data
@TableName("word_books")
@Tag(name = "WordBook", description = "单词书实体")
public class WordBook extends BaseEntity {

    @Schema(description = "单词书名称")
    private String name;

    @Schema(description = "单词书描述")
    private String description;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}
