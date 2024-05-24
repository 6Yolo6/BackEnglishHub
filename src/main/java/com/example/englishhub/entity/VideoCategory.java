package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hahaha
 * @since 2024-05-16
 */
@Data
@Tag(name = "视频类别管理")
@TableName("video_category")
public class VideoCategory extends BaseEntity {
    /**
     * 类别名
     */
    private String name;

    /**
     * 是否禁用1-有效，0-无效
     */
    private Boolean useful;
}
