package com.mrone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.ArticleDto;
import com.mrone.entity.Article;
import com.mrone.mapper.ArticleMapper;
import com.mrone.service.ArticleService;
import com.mrone.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-06-15 21:48
 **/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisUtil redisUtil;



    @Override
    public Result<Object> findByUserId(int uid) {
        Result<Object> result = new Result<>();
//        String openid =String.valueOf(SecurityUtils.getSubject().getPrincipals());
//        if(redisUtil.get(openid)==null){
//            result.setCode(ResultCode.USER_NOT_LOGGED_IN.code())
//                    .setMessage(ResultCode.USER_NOT_LOGGED_IN.message());
//            return result;
//        }
        QueryWrapper<Article> wrapper  = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        List<Article> articles = articleMapper.selectList(wrapper);
        if(articles==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }
        result.setData(articles).setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    @Override
    public Result<Object> findByTag(String tag) {
        Result<Object> result = new Result<>();
        QueryWrapper<Article> wrapper  = new QueryWrapper<>();
        List<ArticleDto> articles = articleMapper.findByTag(tag);
        if(articles==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }
        result.setData(articles).setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    @Override
    public Result<Object> updateByUidAndAid(ArticleDto articleDto) {
        Result<Object> result = new Result<>();
//        String openid =String.valueOf(SecurityUtils.getSubject().getPrincipals());
//        if(redisUtil.get(openid)==null){
//            result.setCode(ResultCode.USER_NOT_LOGGED_IN.code())
//                    .setMessage(ResultCode.USER_NOT_LOGGED_IN.message());
//            return result;
//        }
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",articleDto.getUid()).eq("id",articleDto.getId());
        Article article = articleMapper.selectOne(wrapper);
        if(article==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }else {
            String title = articleDto.getTitle();
            String image = articleDto.getImage();
            String tag = articleDto.getTag();
            String content = articleDto.getContent();
            Date date = new Date();
            UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("content",content).set("image",image).set("title",title).set("tag",tag).set("updatetime",date)
                    .eq("uid",articleDto.getUid()).eq("id",articleDto.getId());
            articleMapper.update(article,updateWrapper);
            result.setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }

        return result;
    }

    @Override
    public Result<Object> findAll(int pages) {
        Result<Object> result = new Result<>();
        //设置分页的数据
        IPage<Article> page=new Page<>();
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        page.setCurrent(pages);//第1页
        page.setSize(10);//每页3条记录
        IPage<Article> articles = articleMapper.selectPage(page,wrapper);
        if(articles==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }else {
            result.setData(articles)
                    .setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }

    @Override
    public Result<Object> delById(int id, int uid) {
        Result<Object> result = new Result<>();
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id).eq("uid",uid);
        Article article = articleMapper.selectOne(wrapper);
        if(article==null){
            result.setCode(ResultCode.DATA_IS_WRONG.code());
            result.setMessage(ResultCode.DATA_IS_WRONG.message());
        }else {
            articleMapper.delete(wrapper);
            result.setMessage(ResultCode.SUCCESS.message());
            result.setCode(ResultCode.SUCCESS.code());
        }
        return result;
    }

    @Override
    public Result<Object> findByUid(int uid) {
        Result<Object> result = new Result<>();
        List<ArticleDto> collectArticle = articleMapper.findByUid(uid);
        if(collectArticle==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }else {
            result.setData(collectArticle)
                    .setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }

    @Override
    public Result<Object> adminDelById(int id) {
        Result<Object> result = new Result<>();
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int delete = articleMapper.delete(wrapper);
        if(delete<0){
            result.setMessage(ResultCode.FAIL.message());
            result.setCode(ResultCode.FAIL.code());
            return result;
        }
        result.setMessage(ResultCode.SUCCESS.message());
        result.setCode(ResultCode.SUCCESS.code());
        return result;
    }

    @Override
    public Result<Object> findHotIndex() {
        Result<Object> result = new Result<>();
        List<ArticleDto> index = articleMapper.findHotIndex();
        if(index==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }else {
            result.setData(index)
                    .setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }

    @Override
    public Result<Object> findNewIndex() {
        Result<Object> result = new Result<>();
        List<ArticleDto> newIndex = articleMapper.findNewIndex();
        if(newIndex==null){
            result.setCode(ResultCode.RESULT_DATA_NONE.code())
                    .setMessage(ResultCode.RESULT_DATA_NONE.message());
            return result;
        }else {
            result.setData(newIndex)
                    .setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }
}




