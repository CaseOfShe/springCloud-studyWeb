package com.mrone.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.ChooseDto;
import com.mrone.entity.Chooseanswer;
import com.mrone.mapper.ChooseanswerMapper;
import com.mrone.service.ChooseanswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 */
@Service
public class ChooseanswerServiceImpl extends ServiceImpl<ChooseanswerMapper, Chooseanswer>
        implements ChooseanswerService {

    @Autowired
    private ChooseanswerMapper chooseanswerMapper;

    @Override
    public Result<Object> findChooseBySid(Integer sid) {
        Result<Object> result = new Result<>();
        ChooseDto chooseBySid = chooseanswerMapper.findChooseBySid(sid);
        if(chooseBySid==null){
            result.setCode(ResultCode.DATA_IS_WRONG.code())
                    .setMessage(ResultCode.DATA_IS_WRONG.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message())
                .setData(chooseBySid);
        return result;
    }

    @Override
    public Result<Object> insertChoose(Chooseanswer chooseanswer) {
        Result<Object> result = new Result<>();
        int insert = chooseanswerMapper.insert(chooseanswer);
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




