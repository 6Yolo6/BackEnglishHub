package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 每日一句表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-10
 */
@Data
@Tag(name = "每日一句管理",description = "每日一句管理")
@TableName("daily_sentences")
public class DailySentences extends BaseEntity {
    /**
     * 英文内容
     */
    private String content;

    /**
     * 翻译内容
     */
    private String translation;

    /**
     * 英文音频文件路径
     */
    private String audioPath;

    /**
     * 图片文件路径
     */
    private String imagePath;

    /**
     * 日期
     */
    private LocalDateTime date;
}
