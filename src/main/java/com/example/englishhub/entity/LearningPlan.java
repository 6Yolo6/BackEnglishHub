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
 * @Date: 2024/4/8 14:06
 */

/**
 * 学习计划实体类
 */
@Data
@TableName("learning_plans")
@Tag(name = "LearningPlan", description = "学习计划实体")
public class LearningPlan extends BaseEntity {
    @Schema(description = "用户ID，关联user表的id")
    private Integer userId;

    @Schema(description = "学习计划标题")
    private String planTitle;

    @Schema(description = "学习单词库的某类别，如高频、核心、闪过等")
    private Integer wordCategory;

    @Schema(description = "每日学习和复习总词数")
    private Integer dailyWords;

    @Schema(description = "计划开始日期")
    private Timestamp startDate;

    @Schema(description = "计划结束日期")
    private Timestamp endDate;

    @Schema(description = "计划状态：1-active, 2-completed, 3-paused")
    private Integer status;

    @Schema(description = "遗忘曲线复习间隔分钟如1440,2880,5760,10080,21600(分别对应1天,2天,4天,7天,15天)")
    private String reviewIntervals;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}
