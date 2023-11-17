package com.mrone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrone.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDtoMapper extends BaseMapper<CommentDto> {
    List<CommentDto> findComment(int aid);

}
