package com.mrone.dto;

import lombok.Data;

import java.util.Date;

/**
 * @program: shiro
 * @description: 评论
 * @author: Mr.One
 * @create: 2022-07-29 10:36
 **/
@Data
public class CommentDto {
    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 父id
     */
    private Integer fid;

    /**
     * 父评论内容
     */
    private String fComment;

    /**
     * 图片
     */
    private String fImage;

    /**
     * 评论时间
     */
    private Date fTime;

    /**
     * 子评论者的连接id
     */
    private Integer cid;

    /**
     * 内容
     */
    private String cComment;

    /**
     * 图片
     */
    private String cImage;

    /**
     * 评论时间
     */
    private Date cTime;
}
