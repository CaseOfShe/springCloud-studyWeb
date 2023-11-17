package com.mrone.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-16 15:47
 **/
@Data
public class ChooseDto {
    /**
     * 题库id
     */
    private Integer sid;

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

    /**
     * 选择题题目
     */
    private String choosename;

    /**
     * A
     */
    private String anwsera;

    /**
     * B
     */
    private String anwserb;

    /**
     * C
     */
    private String anwserc;

    /**
     * D
     */
    private String anwserd;
}

