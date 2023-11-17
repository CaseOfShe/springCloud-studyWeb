package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.Comment;

import java.util.List;

/**
 *
 */
public interface CommentService extends IService<Comment> {

        List<Comment> findArticleComment(int aid);

        Result<Object> insertResultComment(int aid,int fid,String comment);

        Result<Object> adminCommentDel(int aid,int fid);

}
