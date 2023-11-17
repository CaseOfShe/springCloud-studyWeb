package com.mrone.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mrone.commons.Result;
import com.mrone.dto.AdminDto;
import com.mrone.entity.Admin;

/**
 *
 */
public interface AdminService extends IService<Admin> {

    Admin findByPhone2(String phone);

    AdminDto findByPhone(String phone);

    Result<Object> addAdmin(String phone,String name,String password,String role,String permission);

    Result<Object> forgetPassword(String phone,String code,String password);

}
