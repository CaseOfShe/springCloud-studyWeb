package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.UserDto;
import com.mrone.feignclients.UserClient;
import com.mrone.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-12 18:07
 **/
@RestController
@RequestMapping("/article")
public class IndexController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/user")
    public Result<Object> indexUserInfo(){
        Result<Object> result = new Result<>();
        UserDto one = userClient.indexUserInfo();
        if(one==null){
            result.setCode(ResultCode.DATA_IS_WRONG.code())
                    .setMessage(ResultCode.DATA_IS_WRONG.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message())
                .setData(one);
        return result;
    }

    @GetMapping("/hot")
    public Result<Object> indexHotArticleInfo(){
        Result<Object> hotIndex = articleService.findHotIndex();
        return hotIndex;
    }

    @GetMapping("/new")
    public Result<Object> indexNewArticleInfo(){
        Result<Object> newIndex = articleService.findNewIndex();
        return newIndex;
    }


}
