package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.CommentDto;
import com.mrone.service.ChildcommentService;
import com.mrone.service.CommentDtoService;
import com.mrone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: shiro
 * @description: 评论
 * @author: Mr.One
 * @create: 2022-07-28 13:31
 **/
@Controller
@ResponseBody
@RequestMapping("/article")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ChildcommentService childcommentService;

    @Autowired
    private CommentDtoService commentDtoService;

    @PostMapping("/comment/detail")
    public Result<Object>comments(@RequestParam("aid") int aid){
        Result<Object> result = new Result<>();
        List<CommentDto> comment = commentDtoService.findComment(aid);
        if(comment==null){
            result.setCode(ResultCode.DATA_IS_WRONG.code())
                    .setMessage(ResultCode.DATA_IS_WRONG.message());
            return result;
        }
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message())
                .setData(comment);
        return result;

    }



    @PostMapping("/fcomment/insert")
    public Result<Object> articleTell(int aid, int fid, String fComment){
            Result<Object> result = commentService.insertResultComment(aid, fid, fComment);
            return result;
    }


    @PostMapping("/ccomment/insert")
    public Result<Object> commentTell(int fid, int cid, String cComment){
        Result<Object> result = childcommentService.insertResultComment(cid, fid, cComment);
        return result;
    }


    @PostMapping("/del")
    public Result<Object> commentDel(int aid,int fid,int cid) {
        Result<Object> result = commentService.adminCommentDel(aid, fid);
        childcommentService.adminChildCommentDel(fid,cid);
        return result;
    }

}

//    @PostMapping("/detail")
//    public Result<Object> comments(int aid){
//        Result<Object> result = new Result<>();
//        List<Comment> articleComment = commentService.findArticleComment(aid);
//        List<CommentDto> commentDto = new ArrayList<>();
//        if(articleComment==null){
//            return null;
//        }
//        for (Comment comment : articleComment) {
//            CommentDto Dto = new CommentDto();
//            Integer fid = comment.getFid();
//            List<Childcomment> commentByFid = childcommentService.findCommentByFid(fid);
//            if(commentByFid==null){
//               Dto.setAid(comment.getAid());
//               Dto.setFComment(comment.getFcomment());
//               Dto.setFid(comment.getFid());
//               Dto.setFImage(comment.getFimage());
//               Dto.setFTime(comment.getFtime());
//               commentDto.add(Dto);
//               result.setData(commentDto);
//               result.setMessage("无子级评论");
//               result.setCode(1);
//            }
//            for (Childcomment childcomment : commentByFid) {
//                Dto.setAid(comment.getAid());
//                Dto.setFComment(comment.getFcomment());
//                Dto.setFid(comment.getFid());
//                Dto.setFImage(comment.getFimage());
//                Dto.setFTime(comment.getFtime());
//                Dto.setCid(childcomment.getCid());
//                Dto.setCComment(childcomment.getCcomment());
//                Dto.setCImage(childcomment.getCimage());
//                Dto.setCTime(childcomment.getCtime());
//                commentDto.add(Dto);
//                result.setData(commentDto);
//                result.setMessage("有子级评论");
//                result.setCode(2);
//            }
//        }
//        return result;
//    }