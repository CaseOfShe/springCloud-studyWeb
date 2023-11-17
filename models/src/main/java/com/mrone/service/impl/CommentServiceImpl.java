package com.mrone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.entity.Childcomment;
import com.mrone.entity.Comment;
import com.mrone.mapper.CommentMapper;
import com.mrone.service.ChildcommentService;
import com.mrone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ChildcommentService childcommentService;

    @Override
    public List<Comment> findArticleComment(int aid) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("aid",aid);
        List<Comment> comment = commentMapper.selectList(wrapper);
        if(comment==null){
            return null;
        }
        return comment;
    }



    @Override
    public Result<Object> insertResultComment(int aid, int uid, String comment) {
        Result<Object> result = new Result<>();
        Date date = new Date();
        Comment comments = new Comment();
        comments.setAid(aid);
        comments.setFid(uid);
        comments.setFcomment(comment);
        comments.setFtime(date);
        commentMapper.insert(comments);
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    @Override
    public Result<Object> adminCommentDel(int aid,int fid) {
        Result<Object> result = new Result<>();
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("aid",aid).eq("fid",fid);
        Comment comment = commentMapper.selectOne(wrapper);
        List<Childcomment> commentByFid = childcommentService.findCommentByFid(fid);
        if(null!=comment){
            commentMapper.delete(wrapper);
            if (null!=comment){
                for (Childcomment childcomment : commentByFid) {
                    childcommentService.adminChildCommentDel(fid,childcomment.getCid());
                }
            }
            result.setCode(200);
            result.setMessage("删除成功");
            return result;
        }
        result.setCode(500);
        result.setMessage("删除失败");
        return result;
    }



}




