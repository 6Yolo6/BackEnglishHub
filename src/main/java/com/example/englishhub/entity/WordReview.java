package com.example.englishhub.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author: hahaha
 * @Date: 2024/4/8 10:30
 * 单词复习状态表
 */

@Data
@TableName("word_review")
@Tag(name = "WordReview", description = "单词复习状态实体")
public class WordReview extends BaseEntity {

    @Schema(description = "用户ID，关联user表的id")
    private Integer userId;

    @Schema(description = "单词ID，关联words表或word表的id")
    private Integer wordId;

    @Schema(description = "单词类别ID，关联categories表的id")
    private Integer categoryId;

    @Schema(description = "单词复习状态:1-forgotten,2-blurry,3-known,4-mastered")
    private Integer status;

    @Schema(description = "下次复习时间")
    private LocalDateTime nextReviewTime;

    @Schema(description = "当前单词复习间隔索引，对应learning_plans表中的review_interval")
    private Integer reviewIntervalIndex;

    @Schema(description = "上次复习时间")
    private LocalDateTime lastReviewTime;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}
