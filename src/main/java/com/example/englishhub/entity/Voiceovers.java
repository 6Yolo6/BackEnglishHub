package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 每日一句配音表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-11
 */
@TableName("voiceovers")
@Data
@Tag(name = "每日一句配音管理",description = "每日一句配音管理")
public class Voiceovers extends BaseEntity {

    /**
     * 音频文件路径
     */
    private String audioUrl;

    /**
     * 用户ID（配音者的外键）
     */
    private Integer userId;

    /**
     * 句子ID（对应每日一句句的外键）
     */
    private Integer sentenceId;

    /**
     * 点赞数，默认为0
     */
    private Integer likeCount;
}
