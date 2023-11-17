package com.mrone.controller.feign;

import com.mrone.commons.Result;
import com.mrone.service.ArticleService;
import com.mrone.service.ChildcommentService;
import com.mrone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 19:08
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ChildcommentService childcommentService;

    @PostMapping("/farticle")
    public Result<Object> feignAdminArticle(int id){
        Result<Object> result = articleService.adminDelById(id);
        return result;
    }

    @PostMapping("/fcomment")
    public Result<Object> feignAdminFComment(int aid,int fid){
        Result<Object> result = commentService.adminCommentDel(aid,fid);
        return result;
    }

    @PostMapping("/fccomment")
    public Result<Object> feignAdminCComment(int uid,int cid){
        Result<Object> result = childcommentService.adminChildCommentDel(uid,cid);
        return result;
    }


}
