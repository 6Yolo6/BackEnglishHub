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
 * 视频资源表
 * </p>
 *
 * @author hahaha
 * @since 2024-05-16
 */
@Data
@Tag(name = "Videos", description = "视频资源表")
@TableName("videos")
public class Videos extends BaseEntity {
    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 视频文件存储路径
     */
    private String filePath;

    /**
     * 视频时长
     */
    private String duration;

    /**
     * 视频分类id,对应video_category表
     */
    private Integer category;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 附加信息，如对应剧本pdf，对应讲义pdf，则对应e_books的id
     */
    private Integer additionInfo;
}
