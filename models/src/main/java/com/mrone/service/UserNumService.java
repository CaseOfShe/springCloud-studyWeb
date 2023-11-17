package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.UserNum;


/**
 *
 */
public interface UserNumService extends IService<UserNum> {
    Result<Object> addUserNum(String openid);

    Result<Object> delById(int id);

    String findOpenidById(int id);

    UserNum findByOpenid(String openid);
}
