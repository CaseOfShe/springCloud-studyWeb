package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.dto.UserDto;
import com.mrone.entity.User;

import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {


     Result<Object> registerAndLogin(String openid);

     User findByOpenid(String openid);

     Result<Object> delByOpenid();

     Result<Object> updateRoleAndPermission(String role,String permission);

     UserDto findOne();

     Result<Object> delByOpenid(String openid);

     List<UserDto> findAll();

     Result<Object> updateUserInfo(Integer id,String username);

     Result<Object> updateAvatar(Integer id,String avatar);

     Result<Object> findById(Integer id);

}
