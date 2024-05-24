package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收藏表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-17
 */
@Data
@Tag(name = "Favorites", description = "收藏实体")
@TableName("favorites")
public class Favorites extends BaseEntity {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 资源ID
     */
    private Integer resourceId;

    /**
     * 资源类型（e_books, articles, daily_sentences, videos）
     */
    private String resourceType;

    /**
     * 收藏时间
     */
    private LocalDateTime createTime;
}
