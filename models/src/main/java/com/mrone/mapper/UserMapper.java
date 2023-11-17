package com.mrone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrone.dto.UserDto;
import com.mrone.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity .domain.com.mrone.entity.com.mrone.entity.com.mrone.entity.User.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    UserDto findOne(String openid);

    int updateRoleAndPermission(String role,String permission,String openid);

    List<UserDto> findAll();

    //信息更新
    int updateUserInfo(Integer id,String username);

    int updateAvatar(Integer id,String avatar);

    UserDto findById(Integer id);


}




