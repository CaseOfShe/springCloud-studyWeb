package com.mrone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.entity.User;
import com.mrone.entity.UserNum;
import com.mrone.mapper.UserMapper;
import com.mrone.mapper.UserNumMapper;
import com.mrone.service.UserNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserNumServiceImpl extends ServiceImpl<UserNumMapper, UserNum>
    implements UserNumService {

    @Autowired
    private UserNumMapper userNumMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public Result<Object> addUserNum(String openid) {
        Result<Object> result = new Result<>();
        QueryWrapper<UserNum> wrapper = new QueryWrapper();
        wrapper.eq("openid",openid);
        UserNum num= userNumMapper.selectOne(wrapper);
        if(num!=null){
            result.setMessage("用户已存在");
            result.setCode(-1);
        }else {
            UserNum userNum = new UserNum();
            userNum.setOpenid(openid);
            userNumMapper.insert(userNum);
            result.setMessage("添加成功");
            result.setCode(1);
        }
        return result;
    }

    @Override
    public Result<Object> delById(int id) {
        Result<Object> result = new Result<>();
        QueryWrapper<UserNum> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        UserNum userNum = userNumMapper.selectOne(wrapper);
        if(userNum==null){
            result.setCode(ResultCode.USER_NOT_EXIST.code());
            result.setMessage(ResultCode.USER_NOT_EXIST.message());
        }else {
            String openid = userNum.getOpenid();
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("openid",openid);
            userMapper.delete(userWrapper);
            userNumMapper.delete(wrapper);
            result.setCode(ResultCode.SUCCESS.code());
            result.setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }

    @Override
    public String findOpenidById(int id) {
        QueryWrapper<UserNum> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        UserNum userNum = userNumMapper.selectOne(wrapper);
        if(userNum==null){
            return null;
        }
        return userNum.getOpenid();
    }

    @Override
    public UserNum findByOpenid(String openid) {
        QueryWrapper<UserNum> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UserNum userNum = userNumMapper.selectOne(wrapper);
        return userNum;
    }

}




