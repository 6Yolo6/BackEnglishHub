package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 单词书关联表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-13
 */
@Data
@Tag(name = "单词书关联表管理",description = "单词书关联表管理")
@TableName("word_relation")
public class WordRelation extends BaseEntity{

    /**
     * 关联word_book表，单词书
     */
    private Integer wordBookId;

    /**
     * 关联word表，单词
     */
    private Integer wordId;
}
