package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * <p>
 * 电子书系列表
 * </p>
 *
 * @author hahaha
 * @since 2024-04-15
 */
@Data
@Tag(name = "EBookSeries", description = "电子书系列表")
@TableName("e_book_series")
public class EBookSeries extends BaseEntity {

    /**
     * 系列名称
     */
    private String name;

    /**
     * 是否可用0--不可用 1--可用
     */
    private Boolean useful;
}
