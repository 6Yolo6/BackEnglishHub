package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * <p>
 * 保存用户阅读进度
 * </p>
 *
 * @author hahaha
 * @since 2024-04-17
 */
@Data
@Tag(name = "ReadingProgress", description = "保存用户阅读进度")
@TableName("reading_progress")
public class ReadingProgress extends BaseEntity {

    /**
     * 关联用户
     */
    private Integer userId;

    /**
     * 关联电子书e_books
     */
    private Integer eBookId;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 当前页码
     */
    private Integer currentPage;

    /**
     * 上次阅读时间
     */
    @UpdateTimestamp
    private Timestamp lastReadTime;
}
