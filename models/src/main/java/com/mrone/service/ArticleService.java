package com.mrone.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.dto.ArticleDto;
import com.mrone.entity.Article;

/**
 *
 */
public interface ArticleService extends IService<Article> {
    Result<Object> findByUserId(int uid);

    Result<Object> findByTag(String tag);

    Result<Object> updateByUidAndAid(ArticleDto articleDto);

    Result<Object> findAll(int pages);

    Result<Object> delById(int id,int uid);

    //根据用户id查询收藏的文章
    Result<Object> findByUid(int uid);

    Result<Object> adminDelById(int id);


    Result<Object> findHotIndex();

    Result<Object> findNewIndex();





}
