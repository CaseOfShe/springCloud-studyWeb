package com.mrone.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.entity.Collect;
import com.mrone.mapper.CollectMapper;
import com.mrone.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
    implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Result<Object> collect(int aid, int uid) {
        Result<Object> result = new Result<>();
        QueryWrapper<Collect> wrapper  = new QueryWrapper<>();
        wrapper.eq("aid",aid).eq("uid",uid);
        Collect collect = collectMapper.selectOne(wrapper);
        if(null==collect){
            Collect newCollect = new Collect();
            newCollect.setAid(aid);
            newCollect.setUid(uid);
            newCollect.setCollect((byte) 1);
            collectMapper.insert(newCollect);
            result.setMessage("收藏成功");
        }else {
            int delete = collectMapper.delete(wrapper);
            result.setMessage("已取消收藏");
        }

        return result;
    }
}




