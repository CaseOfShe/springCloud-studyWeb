package com.mrone.controller;


import com.mrone.commons.Result;
import com.mrone.entity.Chooseanswer;
import com.mrone.entity.Shortanswer;
import com.mrone.entity.Subject;
import com.mrone.service.ChooseanswerService;
import com.mrone.service.ShortanswerService;
import com.mrone.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: shiro
 * @description:
 * @author: Mr.One
 * @create: 2022-09-12 10:49
 **/
@RestController
@RequestMapping("/article")
public class SubjectController {

    @Autowired
    private ChooseanswerService chooseanswerService;

    @Autowired
    private ShortanswerService shortanswerService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject/choose")
    public Result<Object> chooseBySid(Integer sid){
        Result<Object> chooseBySid = chooseanswerService.findChooseBySid(sid);
        return chooseBySid;
    }

        @GetMapping("/subject/short")
    public Result<Object> shortBySid(Integer sid){
        Result<Object> shortBySid = shortanswerService.findShortBySid(sid);
        return shortBySid;
    }

    @PostMapping("/subject/insert/choose")
    public Result<Object> insertChoose(Subject subject, Chooseanswer chooseanswer){
        subjectService.insertSubject(subject);
        Result<Object> result = chooseanswerService.insertChoose(chooseanswer);
        return result;
    }

    @PostMapping("/subject/insert/short")
    public Result<Object> insertShort(Subject subject, Shortanswer shortanswer){
        subjectService.insertSubject(subject);
        Result<Object> result = shortanswerService.insertShort(shortanswer);
        return result;
    }



}
