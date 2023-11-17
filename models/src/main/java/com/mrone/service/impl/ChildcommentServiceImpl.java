package com.mrone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.entity.Childcomment;
import com.mrone.mapper.ChildcommentMapper;
import com.mrone.service.ChildcommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 *
 */
@Service
public class ChildcommentServiceImpl extends ServiceImpl<ChildcommentMapper, Childcomment>
        implements ChildcommentService {

    @Autowired
    private ChildcommentMapper childcommentMapper;

    @Override
    public List<Childcomment> findCommentByFid(int fid) {
        QueryWrapper<Childcomment> wrapper = new QueryWrapper<>();
        wrapper.eq("fid",fid);
        List<Childcomment> childcomments = childcommentMapper.selectList(wrapper);
        if(childcomments==null){
            return null;
        }
        return childcomments;
    }

    @Override
    public Result<Object> insertResultComment(int cid, int fid, String comment) {
        Result<Object> result = new Result<>();
        Date date = new Date();
        Childcomment childcomment = new Childcomment();
        childcomment.setCcomment(comment);
        childcomment.setFid(fid);
        childcomment.setCid(cid);
        childcomment.setCtime(date);
        childcommentMapper.insert(childcomment);
        result.setCode(ResultCode.SUCCESS.code());
        result.setMessage(ResultCode.SUCCESS.message());
        return result;
    }



    @Override
    public Result<Object> adminChildCommentDel(int fid,int cid) {
        Result<Object> result = new Result<>();
        QueryWrapper<Childcomment> wrapper = new QueryWrapper<>();
        wrapper.eq("fid",fid).eq("cid",cid);
        Childcomment comment = childcommentMapper.selectOne(wrapper);
        if(comment!=null){
            childcommentMapper.delete(wrapper);
            result.setCode(200);
            result.setMessage("删除成功");
            return result;
        }
        result.setCode(500);
        result.setMessage("删除失败");
        return result;
    }



}




