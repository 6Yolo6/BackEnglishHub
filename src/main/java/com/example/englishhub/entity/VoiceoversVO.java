package com.example.englishhub.entity;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: hahaha
 * @Date: 2024/5/11 20:02
 */

@Data
@ToString
public class VoiceoversVO {
    private Integer id;
    private String audioUrl;
    private Integer userId;
    private String username;
    private String avatar;
    private Integer sentenceId;
    private Integer likeCount;
    private Timestamp createTime;
}
