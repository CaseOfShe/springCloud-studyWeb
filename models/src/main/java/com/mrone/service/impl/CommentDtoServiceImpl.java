package com.mrone.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.dto.CommentDto;
import com.mrone.mapper.CommentDtoMapper;
import com.mrone.service.CommentDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: shiro
 * @description: Dto
 * @author: Mr.One
 * @create: 2022-07-29 13:58
 **/
@Service
public class CommentDtoServiceImpl extends ServiceImpl<CommentDtoMapper, CommentDto>
    implements CommentDtoService {
    @Autowired
    private CommentDtoMapper commentDtoMapper;
    @Override
    public List<CommentDto> findComment(int aid) {
        List<CommentDto> comment = commentDtoMapper.findComment(aid);
        return comment;
    }
}
