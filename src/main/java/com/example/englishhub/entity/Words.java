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
 * @Date: 2024/4/8 14:13
 */

/**
 * 单词详细表
 * @hahaha
 */

@Data
@TableName("words")
@Tag(name = "Words", description = "单词详细实体")
public class Words {

    @Schema(description = "单词id")
    private Integer id;

    @Schema(description = "单词")
    private String word;

    @Schema(description = "单词释义信息")
    private String definition;

    @Schema(description = "单词发音url")
    private String audioUrl;

    @Schema(description = "单词视频url")
    private String videoUrl;

    @Schema(description = "单词视频字幕")
    private String subtext;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}
