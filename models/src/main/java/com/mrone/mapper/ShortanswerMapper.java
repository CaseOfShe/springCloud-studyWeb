package com.mrone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrone.dto.ShortDto;
import com.mrone.entity.Shortanswer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity .entity.Shortanswer
 */
@Mapper
public interface ShortanswerMapper extends BaseMapper<Shortanswer> {
    ShortDto findShortBySid(Integer sid);
}




