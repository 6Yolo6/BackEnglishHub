package com.example.englishhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author hahaha
 * 2024-3-14
 */
@Data
@TableName("user")
public class User extends BaseEntity {

    private String username;

    private String nickname;

    private String password;

    private Integer privilege;

    private String salt;

    private String telephone;

    private String address;
}
