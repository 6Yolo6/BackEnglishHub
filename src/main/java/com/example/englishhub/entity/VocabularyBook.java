package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 生词本表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
@Data
@Tag(name = "VocabularyBook", description = "生词本实体")
@TableName("vocabulary_book")
public class VocabularyBook extends BaseEntity {
    /**
     * 用户ID,关联users表
     */
    private Integer userId;

    /**
     * 单词ID,关联word表
     */
    private Integer wordId;

    /**
     * 添加时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
