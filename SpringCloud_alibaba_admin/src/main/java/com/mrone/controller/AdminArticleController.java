package com.mrone.controller;

import com.mrone.commons.Result;
import com.mrone.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
public class AdminArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/article/show")
    public Result<Object> adminArticleShow(int pages){
        Result<Object> all = articleService.findAll(pages);
        return all;
    }

    @RequiresAuthentication
    @RequiresRoles("admin")
    @RequiresPermissions("admin")
    @PostMapping("/article/del")
    public Result<Object> adminArticleDel(int id){
        Result<Object> result = articleService.adminDelById(id);
        return result;
    }
}
