package com.mrone.service.impl;

import com.mrone.commons.Result;
import com.mrone.service.RedisStarService;
import com.mrone.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-07-31 15:07
 **/
@Service
public class RedisStarServiceImpl implements RedisStarService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 进行点赞
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public Result<Object> articleAddStar(int aid, int uid) {
        Result<Object> result = new Result<>();
        Set<Object> objects = redisUtil.sGet("star" + aid);
        System.out.println(objects);
        if(objects!=null){
            redisUtil.hdel("star"+aid);
            result.setMessage("已取消点赞");
            result.setData(-1);
        }else {
            redisUtil.set("star" + aid, uid);
            result.setMessage("点赞成功");
            result.setData(1);
        }
        return result;
    }

    /**
     * 点赞数量统计
     * @param aid
     * @return
     */
    @Override
    public int countStar(int aid) {
        int l =(int) redisUtil.lGetListSize("star" + aid);
        return l;
    }

    /**
     * 点赞状态
     * @param aid
     * @param uid
     * @return
     */
    @Override
    public int starStatus(int aid, int uid) {
        boolean b = redisUtil.sHasKey("star" + aid, uid);
        return b?1:0;
    }


}
