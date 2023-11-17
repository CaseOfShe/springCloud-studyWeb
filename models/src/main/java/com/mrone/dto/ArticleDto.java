package com.mrone.dto;

import lombok.Data;

/**
 * @program: shiro
 * @description: 文章Dto
 * @author: Mr.One
 * @create: 2022-07-27 12:29
 **/
@Data
public class ArticleDto {

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 连接openid
     */
    private Integer uid;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片
     */
    private String image;

    /**
     * 类型
     */
    private String tag;

    private Integer count;

}
