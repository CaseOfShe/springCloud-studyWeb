package com.mrone.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrone.commons.Result;
import com.mrone.commons.ResultCode;
import com.mrone.dto.AdminDto;
import com.mrone.entity.Admin;
import com.mrone.mapper.AdminMapper;
import com.mrone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

    @Autowired
    private  AdminMapper adminMapper;

    @Override
    public Admin findByPhone2(String phone) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",phone);
        Admin admin = adminMapper.selectOne(wrapper);
        if(admin==null){
            return null;
        }
        return admin;
    }

    @Override
    public AdminDto findByPhone(String phone) {
        AdminDto admin =adminMapper.findByPhone(phone);
        if(admin==null){
            return null;
        }
        return admin;
    }

    @Override
    public Result<Object> addAdmin(String phone, String name, String password, String role, String permission) {
        Result<Object> result = new Result<>();
        Admin admin = new Admin();
        admin.setPhone(phone);
        admin.setName(name);
        admin.setPassword(password);
        admin.setRole(role);
        admin.setPermission(permission);
        adminMapper.insert(admin);
        result.setCode(ResultCode.SUCCESS.code())
                .setMessage(ResultCode.SUCCESS.message());
        return result;
    }

    @Override
    public Result<Object> forgetPassword(String phone, String code, String password) {
        Result<Object> result = new Result<>();
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        QueryWrapper<Admin> admin = wrapper.eq("phone", phone);
        return result;
    }
}




