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
 * <p>
 * 学习计划表
 * </p>
 *
 * @author hahaha
 * @since 2024-04-19
 */
@Data
@TableName("learning_plans")
@Tag(name = "LearningPlans", description = "学习计划实体")
public class LearningPlans extends BaseEntity {

    /**
     * 用户ID，关联user表的id
     */
    private Integer userId;


    /**
     * 关联word_books表里的id，学习某本单词书
     */
    private Integer wordBookId;

    /**
     * 每日新学词数
     */
    private Integer daily_new_words;

    /**
     * 每日复习词数
     */
    private Integer daily_review_words;

    /**
     * 计划开始日期
     */
    private LocalDateTime startDate;

    /**
     * 计划结束日期
     */
    private LocalDateTime endDate;

    /**
     * 计划状态：1-active, 2-completed, 3-paused
     */
    private Integer status;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}
