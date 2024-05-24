package com.example.englishhub.entity;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Author: hahaha
 * @Date: 2024/4/28 22:16
 */

@Data
@ToString
public class WordReviewVO {
    private Integer id; // 单词id，来自word表
    private String word; // 单词，来自word表
    private String phoneticUk; // 英式音标，来自word表
    private String phoneticUs; // 美式音标，来自word表
    private String definition; // 单词释义，来自word表
    private String wordsDefinition; // 单词释义详细信息，来自words表
    private String audioUrl; // 单词发音url，来自words表
    private String videoUrl; // 单词视频url，来自words表
    private String subtext; // 单词视频字幕，来自words表
}
