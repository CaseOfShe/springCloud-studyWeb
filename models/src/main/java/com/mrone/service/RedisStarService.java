package com.mrone.service;


import com.mrone.commons.Result;

/**
 * @program: shiro
 * @description: redis
 * @author: Mr.One
 * @create: 2022-07-31 15:07
 **/
public interface RedisStarService {

    Result<Object> articleAddStar(int aid, int uid);

    int countStar(int aid);

    int starStatus(int aid,int uid);

//    int commentAddStar(int aid,int uid,int lid);

}
