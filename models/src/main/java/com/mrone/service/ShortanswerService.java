package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.Shortanswer;


/**
 *
 */
public interface ShortanswerService extends IService<Shortanswer> {
    Result<Object> findShortBySid(Integer sid);

    Result<Object> insertShort(Shortanswer shortanswer);

}
