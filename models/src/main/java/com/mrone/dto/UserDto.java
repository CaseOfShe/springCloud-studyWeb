package com.mrone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: shiro
 * @description: 传给前端的实体类
 * @author: Mr.One
 * @create: 2022-07-26 12:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    /**
     * 第几个注册的
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色
     */
    private String role;

    /**
     * 权限
     */
    private String permission;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 头像
     */
    private String avatar;
}
