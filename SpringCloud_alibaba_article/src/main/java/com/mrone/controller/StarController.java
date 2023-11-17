package com.mrone.controller;

import com.mrone.aop.CurrentLimiting;
import com.mrone.commons.Result;
import com.mrone.service.CollectService;
import com.mrone.service.RedisStarService;
import com.mrone.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: shiro
 * @description: 点赞
 * @author: Mr.One
 * @create: 2022-07-31 15:16
 **/
@Controller
@RequestMapping("/article")
@ResponseBody
public class StarController {
    @Autowired
    private RedisStarService redisStarService;

    @Autowired
    private StarService starService;


    @Autowired
    private CollectService collectService;

    @CurrentLimiting()
    @PostMapping("/star/like")
    public Result<Object> star(int aid, int uid){
        Result<Object> result = starService.articleAddStar(aid, uid);
        return  result;
    }

    @PostMapping("/star/count")
    public int starCount(int aid){
        int count = starService.countStar(aid);
        return count;
    }

    @CurrentLimiting()
    @PostMapping("/star/collect")
    public Result<Object> collect(int aid,int uid){
        Result<Object> collect = collectService.collect(aid, uid);
        return collect;
    }


}
