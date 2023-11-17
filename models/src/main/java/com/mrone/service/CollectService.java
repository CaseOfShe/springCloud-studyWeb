package com.mrone.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.Collect;

/**
 *
 */
public interface CollectService extends IService<Collect> {
    //收藏添加
    Result<Object> collect(int aid, int uid);

}
