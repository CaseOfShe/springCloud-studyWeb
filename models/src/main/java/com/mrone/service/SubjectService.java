package com.mrone.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.entity.Subject;

/**
 *
 */
public interface SubjectService extends IService<Subject> {

    Integer insertSubject(Subject subject);
}
