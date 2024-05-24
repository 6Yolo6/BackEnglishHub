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
 * 新闻热词表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-15
 */
@Data
@Tag(name = "NewsHotwords", description = "新闻热词")
@TableName("news_hotwords")
public class NewsHotwords extends BaseEntity {


    /**
     * 标题
     */
    private String title;

    /**
     * 热词链接
     */
    private String url;

    /**
     * 图片链接
     */
    private String imageUrl;

    /**
     * 描述
     */
    private String description;

    /**
     * 详细内容（JSON字符串）
     */
    private String content;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 来源名称
     */
    private String sourceName;
}
