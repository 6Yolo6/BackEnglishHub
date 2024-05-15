package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 单词书类别表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-15
 */

@Data
@Tag(name = "WordBookCategory", description = "单词书类别实体")
@TableName("word_book_category")
public class WordBookCategory extends BaseEntity {
    /**
     * 单词书类别名称——四级、六级、中考等
     */
    private String name;

    /**
     * 是否启用
     */
    private Byte useful;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
