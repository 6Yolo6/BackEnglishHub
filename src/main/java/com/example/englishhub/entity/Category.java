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
 * @Date: 2024/4/8 14:18
 */

/**
 * 类别实体类
 */
@Data
@TableName("categories")
@Tag(name = "Category", description = "类别实体")
public class Category extends BaseEntity {

    @Schema(description = "单词书ID，关联word_books表的id")
    private Integer wordBookId;

    @Schema(description = "类别名称")
    private String name;

    @Schema(description = "类别描述")
    private String description;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;
}