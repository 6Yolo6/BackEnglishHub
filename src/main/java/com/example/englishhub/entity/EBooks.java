package com.example.englishhub.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;

/**
 * 电子书表(EBooks)表实体类
 *
 * @author makejava
 * @since 2024-04-14 20:51:34
 */

@Data
@TableName("e_books")
@Tag(name = "EBooks", description = "电子书表(EBooks)表实体类")
public class EBooks extends BaseEntity {

    //名称
    private String title;

    //作者
    private String author;

    //存储OSS中的文件路径
    private String filePath;

    //pdf 或 epub
    private String fileType;

    //系列id,来自e_book_series表
    private Integer seriesId;

    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;

    @Override
    public String toString() {
        return "EBooks{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileType + '\'' +
                ", seriesId=" + seriesId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

