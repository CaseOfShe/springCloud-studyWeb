package com.mrone.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.Star;

/**
 *
 */
public interface StarService extends IService<Star> {
    Result<Object> articleAddStar(int aid, int uid);

    int countStar(int aid);
}
