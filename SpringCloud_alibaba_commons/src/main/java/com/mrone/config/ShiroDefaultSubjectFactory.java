package com.mrone.config;

import org.apache.shiro.mgt.DefaultSubjectFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;

/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/14 14:34
 * @Description: 使用token验证关闭shiro session
 */
public class ShiroDefaultSubjectFactory extends DefaultSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        // 不创建shiro内部的session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
