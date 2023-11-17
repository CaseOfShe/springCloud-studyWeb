package com.mrone.controller;

import com.mrone.commons.Result;

import com.mrone.service.ChildcommentService;
import com.mrone.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-07 14:59
 **/
@Slf4j
@Controller
@ResponseBody
@RequestMapping("/admin")
public class AdminCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ChildcommentService childcommentService;

    @RequiresAuthentication
    @RequiresRoles("admin")
    @RequiresPermissions("admin")
    @PostMapping("/comment/del")
    public Result<Object> adminCommentDel(int aid,int fid){
        Result<Object> result = commentService.adminCommentDel(aid,fid);
        return result;
    }

    @RequiresAuthentication
    @RequiresRoles("admin")
    @RequiresPermissions("admin")
    @PostMapping("/childcomment/del")
    public Result<Object> adminChildCommentDel(int fid,int cid){
        Result<Object> result = childcommentService.adminChildCommentDel(fid,cid);
        return result;
    }
}
