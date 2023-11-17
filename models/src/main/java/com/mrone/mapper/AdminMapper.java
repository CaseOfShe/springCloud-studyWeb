package com.mrone.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrone.dto.AdminDto;
import com.mrone.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity .entity.com.mrone.entity.Admin
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    AdminDto findByPhone(String phone);

}




