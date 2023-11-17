package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.Chooseanswer;



/**
 *
 */
public interface ChooseanswerService extends IService<Chooseanswer> {
    Result<Object> findChooseBySid(Integer sid);

    Result<Object> insertChoose(Chooseanswer chooseanswer);
}
