package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.dto.CommentDto;

import java.util.List;

public interface CommentDtoService extends IService<CommentDto> {
    List<CommentDto> findComment(int aid);

}
