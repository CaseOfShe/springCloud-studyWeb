package com.mrone.feignclients;

import com.mrone.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "USER",url = "http://localhost:8787",contextId = "User")
public interface UserClient {

    @PostMapping("/feign/byopenid")
    UserDto byOpenid();
}
