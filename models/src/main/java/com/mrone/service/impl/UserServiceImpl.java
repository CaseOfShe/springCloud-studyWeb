package com.mrone.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.UserDto;
import com.mrone.entity.User;
import com.mrone.entity.UserNum;
import com.mrone.mapper.UserMapper;
import com.mrone.service.UserNumService;
import com.mrone.service.UserService;
import com.mrone.util.JwtUtil;
import com.mrone.util.RedisUtil;
import org.apache.shiro.SecurityUtils;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService{

    private static final String AVATAR = "http://i.miaosu.bid/data/f_27797952.jpg";

    @Autowired
    private UserNumService userNumService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;




    @Override
    public Result<Object> registerAndLogin(String openid) {
        Result<Object> result = new Result<>();
        //用户注册
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        User user = userMapper.selectOne(wrapper);
        if(user==null){
            User newUser = new User();
            String name="学习小子"+RandomUtil.randomNumbers(4);
            Date date = new Date();
            userNumService.addUserNum(openid);
            newUser.setAvatar(AVATAR);
            newUser.setUsername(name);
            newUser.setOpenid(openid);
            newUser.setCreatetime(date);
            userMapper.insert(newUser);
            redisUtil.set(openid,openid,3600*2);
            String token = jwtUtil.createToken(openid);
            result.setCode(ResultCode.USER_SUCCESS_LOGIN.code())
                    .setMessage(ResultCode.USER_SUCCESS_LOGIN.message())
                    .setData(token);
            return result;
        }
        String token = jwtUtil.createToken(openid);
        redisUtil.set(openid,openid,3600*2);
        result.setCode(ResultCode.USER_SUCCESS_LOGIN.code())
                .setMessage(ResultCode.USER_SUCCESS_LOGIN.message())
                .setData(token);
        return result;
    }

    @Override
    public User findByOpenid(String openid) {
        Result<Object> result = new Result<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("openid", openid);
        User user = userMapper.selectOne(wrapper);
        if (user == null && redisUtil.get(openid) == null) {
            return null;
        }

        return user;
    }

    @Override
    public Result<Object> delByOpenid() {
        Result<Object> result = new Result<>();
        String openid =String.valueOf(SecurityUtils.getSubject().getPrincipals());
        if(redisUtil.get(openid)==null){
            result.setCode(ResultCode.USER_NOT_LOGGED_IN.code())
                    .setMessage(ResultCode.USER_NOT_LOGGED_IN.message());
            return result;
        }        QueryWrapper<User> wrapper  = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        userMapper.delete(wrapper);

        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    @Override
    public Result<Object> delByOpenid(String openid) {
        Result<Object> result = new Result<>();
        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("openid",openid);
        User user = userMapper.selectOne(wrapper);
        if(user==null){
            result.setCode(ResultCode.USER_NOT_LOGGED_IN.code())
                    .setMessage(ResultCode.USER_NOT_LOGGED_IN.message());
            return result;
        }
        userMapper.delete(wrapper);
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    @Override
    public List<UserDto> findAll() {
        List<UserDto> all = userMapper.findAll();
        return all;
    }



    @Override
    public Result<Object> updateUserInfo(Integer id,String username) {
        Result<Object> result = new Result<>();
        int i = userMapper.updateUserInfo(id, username);
        if(i<=0){
            result.setCode(ResultCode.FAIL.code()).setMessage(ResultCode.FAIL.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;

    }

    @Override
    public Result<Object> updateAvatar(Integer id,String avatar) {
        Result<Object> result = new Result<>();
        int i = userMapper.updateAvatar(id, avatar);
        if(i<=0){
            result.setCode(ResultCode.FAIL.code())
                    .setMessage(ResultCode.FAIL.message());
        }else {
            result.setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }

    @Override
    public Result<Object> findById(Integer id) {
        Result<Object> result = new Result<>();
        UserDto byId = userMapper.findById(id);
        if(byId==null){
            result.setCode(ResultCode.USER_NOT_EXIST.code())
                    .setMessage(ResultCode.USER_NOT_EXIST.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message())
                .setData(byId);
        return result;
    }

    @Override
    public Result<Object> updateRoleAndPermission(String role, String permission) {
        Result<Object> result = new Result<>();
        String openid =String.valueOf(SecurityUtils.getSubject().getPrincipals());
        if(redisUtil.get(openid)==null){
            result.setCode(ResultCode.USER_NOT_LOGGED_IN.code())
                    .setMessage(ResultCode.USER_NOT_LOGGED_IN.message());
            return result;
        }
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("openid",openid);
//        User user = userMapper.selectOne(wrapper);
//        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
//        userUpdateWrapper.set("role",role).set("permission",permission);
//        userMapper.update(user,userUpdateWrapper);
        int i = userMapper.updateRoleAndPermission(role, permission, openid);
        if(i<=0){
            result.setCode(ResultCode.FAIL.code())
                    .setMessage(ResultCode.FAIL.message());
        }else {
            result.setCode(ResultCode.SUCCESS.code())
                    .setMessage(ResultCode.SUCCESS.message());
        }
        return result;
    }

    @Override
    public UserDto findOne() {
        String openid =String.valueOf(SecurityUtils.getSubject().getPrincipals());
        if(redisUtil.get(openid)==null){
            return null;
        }
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("openid",openid);
        QueryWrapper<UserNum> numWrapper = new QueryWrapper<>();
        numWrapper.eq("openid",openid);
        User user= userMapper.selectOne(userWrapper);
        UserNum userNum = userNumService.findByOpenid(openid);
        UserDto userDto = new UserDto();
        userDto.setAvatar(user.getAvatar());
        userDto.setUsername(user.getUsername());
        userDto.setId(userNum.getId());
        return userDto;
    }


}
