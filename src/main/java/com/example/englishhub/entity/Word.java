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
 * 单词实体类
 */

@Data
@TableName("word")
@Tag(name = "Word", description = "单词实体")
public class Word extends BaseEntity {

    @Schema(description = "单词")
    private String word;

    @Schema(description = "单词id，关联到words表详细信息，如果有的话")
    private Integer wordsId;

    @Schema(description = "英音音标")
    private String phoneticUk;

    @Schema(description = "美音音标")
    private String phoneticUs;

    @Schema(description = "英音发音地址")
    private String audioUrlUk;

    @Schema(description = "美音发音地址")
    private String audioUrlUs;

    @Schema(description = "中文释义")
    private String definition;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}