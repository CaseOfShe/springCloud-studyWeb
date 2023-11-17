package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.entity.Childcomment;

import java.util.List;


/**
 *
 */
public interface ChildcommentService extends IService<Childcomment> {

    List<Childcomment> findCommentByFid(int fid);

    Result<Object> insertResultComment(int cid, int fid,String comment);

    Result<Object> adminChildCommentDel(int fid,int cid);


}
