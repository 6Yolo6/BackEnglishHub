package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hahaha
 * @since
 */
@Data
@TableName("user")
@Tag(name = "User", description = "用户实体")
public class User extends BaseEntity implements Serializable {
    /**
     * 用户名，用于登录
     */
    @Schema(description = "用户名，用于登录")
    private String username;

    /**
     * 密码，用于登录
     */
    @Schema(description = "密码，用于登录")
    private String password;

    /**
     * 盐
     */
    @Schema(description = "盐，用于加密")
    private String salt;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱，用于登录")
    private String email;

    /**
     * 手机号
     */
    @Schema(description = "手机号，用于登录")
    private String telephone;

    /**
     * 性别-0--女--1--男
     */
    @Schema(description = "性别：0表示女，1表示男")
    private Byte sex;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @CreationTimestamp
    private Timestamp createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @UpdateTimestamp
    private Timestamp updateTime;

    /**
     * 是否管理员0-不是--1-是
     */
    @Schema(description = "是否管理员：0表示不是，1表示是")
    private Boolean isAdmin;

}
