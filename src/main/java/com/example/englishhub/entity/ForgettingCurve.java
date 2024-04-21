package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author hahaha
 * @since 2024-04-19
 */

@Data
@Tag(name = "ForgettingCurve", description = "遗忘曲线")
@TableName("forgetting_curve")
public class ForgettingCurve extends BaseEntity {

    /**
     * 关联user表
     */
    private Integer userId;

    /**
     * 关联word_review表
     */
    private Integer wordReviewId;

    /**
     * 复习间隔索引，关联learning_plans表
     */
    private Integer reviewIntervalIndex;

    /**
     * 记忆保持率
     */
    private Double retentionRate;

    /**
     * 记录时间
     */
    @UpdateTimestamp
    private Timestamp recordedTime;
}
