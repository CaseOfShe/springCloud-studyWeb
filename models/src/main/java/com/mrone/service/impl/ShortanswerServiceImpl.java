package com.mrone.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.ShortDto;
import com.mrone.entity.Shortanswer;
import com.mrone.mapper.ShortanswerMapper;
import com.mrone.service.ShortanswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 */
@Service
public class ShortanswerServiceImpl extends ServiceImpl<ShortanswerMapper, Shortanswer>
        implements ShortanswerService {

    @Autowired
    private ShortanswerMapper shortanswerMapper;

    @Override
    public Result<Object> findShortBySid(Integer sid) {
        Result<Object> result = new Result<>();
        ShortDto shortBySid = shortanswerMapper.findShortBySid(sid);
        if(shortBySid==null){
            result.setCode(ResultCode.DATA_IS_WRONG.code())
                    .setMessage(ResultCode.DATA_IS_WRONG.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message())
                .setData(shortBySid);
        return result;
    }

    @Override
    public Result<Object> insertShort(Shortanswer shortanswer) {
        Result<Object> result = new Result<>();
        int insert = shortanswerMapper.insert(shortanswer);
        if(insert<=0){
            result.setCode(ResultCode.FAIL.code())
                    .setMessage(ResultCode.FAIL.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;
    }
}




