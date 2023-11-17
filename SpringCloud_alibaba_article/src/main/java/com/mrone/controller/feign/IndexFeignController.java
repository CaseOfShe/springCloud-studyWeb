package com.mrone.controller.feign;

import com.mrone.commons.Result;
import com.mrone.dto.ArticleDto;
import com.mrone.dto.UserDto;
import com.mrone.feignclients.UserClient;
import com.mrone.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-12 18:07
 **/
@RestController
@RequestMapping("/feign")
public class IndexFeignController {
    @Autowired
    private UserClient userClient;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/user")
    public UserDto indexUserInfo(){
        UserDto one = userClient.indexUserInfo();
        return one;
    }

    @GetMapping("/article/hot")
    public Result<Object> indexHotArticleInfo(){
        Result<Object> hotIndex = articleService.findHotIndex();
        return hotIndex;
    }

    @GetMapping("/article/new")
    public Result<Object> indexNewArticleInfo(){
        Result<Object> newIndex = articleService.findNewIndex();
        return newIndex;
    }


}
