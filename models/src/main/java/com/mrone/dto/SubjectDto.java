package com.mrone.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-12 10:11
 **/
@Data
public class SubjectDto {
    /**
     * 题库id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 发布人的uid连接openid
     */
    private Integer uid;

    /**
     * 标签选择
     */
    private String tag;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 题库类型（单选，简答题）
     */
    private String type;

    /**
     * 题目标题
     */
    private String title;

    /**
     * 题内容
     */
    private String content;

    /**
     * 具体是那一篇内容
     */
    private String subject;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色
     */
    private String role;

    /**
     * 头像
     */
    private String avatar;
}
