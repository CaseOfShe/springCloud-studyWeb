package com.mrone.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.entity.Subject;
import com.mrone.mapper.SubjectMapper;
import com.mrone.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject>
        implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Integer insertSubject(Subject subject) {
        int insert = subjectMapper.insert(subject);
        return insert;
    }
}




