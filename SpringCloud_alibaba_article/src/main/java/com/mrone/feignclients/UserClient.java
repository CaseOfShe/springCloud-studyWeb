package com.mrone.feignclients;


import com.mrone.commons.Result;
import com.mrone.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "USER")
public interface UserClient {
    @GetMapping("/feign/user")
    public UserDto indexUserInfo();

    @GetMapping("/feign/user/id")
    public Result<Object> findUserById(@RequestParam("id") Integer id);

}
