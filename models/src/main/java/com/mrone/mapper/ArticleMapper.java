package com.mrone.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrone.dto.ArticleDto;
import com.mrone.entity.Article;

import java.util.List;

/**
 * @Entity .entity.com.mrone.entity.Article
 */
public interface ArticleMapper extends BaseMapper<Article> {


    //根据用户id查询收藏的文章
    List<ArticleDto> findByUid(int uid);

    //根据收藏数排序查询到主页内容
    List<ArticleDto> findHotIndex();

    //根据时间查询主页内容
    List<ArticleDto> findNewIndex();

    //根据类型查找文章
    List<ArticleDto> findByTag(String tag);
}




