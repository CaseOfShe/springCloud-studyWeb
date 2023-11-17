package com.mrone.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-10 15:53
 **/
@Data
public class StarDto implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 点赞用户id
     */
    private Integer uid;

    /**
     * 点赞状态 0取消 1点赞
     */
    private Byte stauts;
}
