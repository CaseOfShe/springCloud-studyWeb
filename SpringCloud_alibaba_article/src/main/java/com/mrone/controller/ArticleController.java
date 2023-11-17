package com.mrone.controller;

import com.mrone.aop.CurrentLimiting;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.ArticleDto;
import com.mrone.entity.Article;
import com.mrone.service.ArticleService;
import com.mrone.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: shiro
 * @description: 文章
 * @author: Mr.One
 * @create: 2022-07-27 12:07
 **/
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CollectService collectService;

    /**
     * 查询所有文章
     * @return
     */
    @PostMapping("/detail")
    public Result<Object> articleDetail(int pages){
        Result<Object> all = articleService.findAll(pages);
        return all;
    }


    /**
     * 根据用户id查询文章
     * @param uid
     * @return
     */
    @PostMapping("/user")
    public Result<Object> articleUser(int uid){
        Result<Object> byUserId = articleService.findByUserId(uid);
        return byUserId;
    }

    /**
     * 根据类型查询
     * @param tag
     * @return
     */
    @PostMapping("/tag")
    public Result<Object> articleTag(String tag){
        Result<Object> byTag = articleService.findByTag(tag);
        return byTag;
    }

    /**
     * 更新文章内容
     * @param articleDto
     * @return
     */
    @PostMapping("update")
    public Result<Object> articleUpdate( ArticleDto articleDto){
        Result<Object> result = articleService.updateByUidAndAid(articleDto);
        return result;
    }

    /**
     * 根据文章id和uid删除文章
     * @param id
     * @param uid
     * @return
     */
    @PostMapping("/delete")
    public Result<Object> articleDelete(int id,int uid){
        Result<Object> result = articleService.delById(id, uid);
        return result;
    }

    /**
     * 根据文章id和uid收藏文章
     * @param id
     * @param uid
     * @return
     */
    @CurrentLimiting()
    @PostMapping("/collect")
    public Result<Object> collect(int id,int uid){
        Result<Object> collect = collectService.collect(id, uid);
        return collect;
    }

    @PostMapping("/collect/find")
    public Result<Object> collect(int uid){
        Result<Object> byUid = articleService.findByUid(uid);
        return byUid;
    }
}
