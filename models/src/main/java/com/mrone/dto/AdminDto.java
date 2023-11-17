package com.mrone.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 17:38
 **/
@Data
public class AdminDto {
    /**
     * 管理员账号
     */
    @TableId
    private String phone;

    /**
     * 管理员姓名
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    /**
     * 权限
     */
    private String permission;
}
