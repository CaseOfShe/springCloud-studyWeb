package com.mrone.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.entity.Star;
import com.mrone.mapper.StarMapper;
import com.mrone.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star>
    implements StarService {

    @Autowired
    private StarMapper starMapper;

    @Override
    public Result<Object> articleAddStar(int aid, int uid) {
        System.out.println(aid);
        System.out.println(uid);
        Result<Object> result = new Result<>();
        QueryWrapper<Star> wrapper = new QueryWrapper<>();
        wrapper.eq("aid",aid).eq("uid",uid);
        Star star = starMapper.selectOne(wrapper);
        if(star==null){
            Star newStar = new Star();
            newStar.setAid(aid);
            newStar.setUid(uid);
            newStar.setStauts((byte) 1);
            starMapper.insert(newStar);
            result.setMessage("点赞成功");
            result.setCode(200);
        }else {
            starMapper.delete(wrapper);
            result.setMessage("已取消点赞");
            result.setCode(500);
        }
        return result;
    }

    @Override
    public int countStar(int aid) {
        QueryWrapper<Star> wrapper = new QueryWrapper<>();
        wrapper.eq("aid",aid);
        List<Star> stars = starMapper.selectList(wrapper);
        return stars.size();
    }
}




