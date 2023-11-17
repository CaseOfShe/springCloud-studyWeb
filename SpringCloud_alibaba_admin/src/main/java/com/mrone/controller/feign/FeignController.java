package com.mrone.controller.feign;

import com.mrone.dto.AdminDto;
import com.mrone.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 18:02
 **/
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/byphone")
    public AdminDto feignByPhone(String phone){
        AdminDto byPhone = adminService.findByPhone(phone);
        return byPhone;
    }


}
