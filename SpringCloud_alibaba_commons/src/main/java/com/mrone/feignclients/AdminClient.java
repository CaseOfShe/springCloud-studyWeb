package com.mrone.feignclients;

import com.mrone.dto.AdminDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 17:55
 **/
@FeignClient(value = "ADMIN",url = "http://localhost:8788",contextId = "User")
public interface AdminClient {
    @PostMapping("/feign/byphone")
     AdminDto feignByPhone(String phone);
}
