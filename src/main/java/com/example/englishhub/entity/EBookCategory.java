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
 *
 * @author hahaha
 * @since 2024-04-25
 */

@Data
@Tag(name = "EBookCategory", description = "电子书类别")
@TableName("e_book_category")
public class EBookCategory extends BaseEntity {

    /**
     * 类别名称
     */
    private String name;

    /**
     * 是否可用0--不可用 1--可用
     */
    private Boolean useful;
}
