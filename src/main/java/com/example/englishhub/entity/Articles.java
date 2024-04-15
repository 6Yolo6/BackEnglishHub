package com.example.englishhub.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.io.Serializable;

/**
 * 文章资源表(Articles)表实体类
 *
 * @author makejava
 * @since 2024-04-11 16:10:53
 */
@Data
@TableName("articles")
@Tag(name = "Articles", description = "文章资源表")
public class Articles extends BaseEntity{

    //文章标题
    @Schema(description = "文章标题")
    private String title;
    //作者
    @Schema(description = "作者")
    private String author;
    //文章描述
    private String description;
    //文章链接
    private String url;
    //图片链接
    private String urlToImage;
    //发布时间
    private Date publishedAt;
    //文章内容
    private String content;
    //来源名称
    private String sourceName;

}

