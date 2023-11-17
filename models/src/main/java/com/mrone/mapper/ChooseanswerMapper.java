package com.mrone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrone.dto.ChooseDto;
import com.mrone.entity.Chooseanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity .entity.com.mrone.entity.Chooseanswer
 */
@Mapper
public interface ChooseanswerMapper extends BaseMapper<Chooseanswer> {
    ChooseDto findChooseBySid(Integer sid);
}




