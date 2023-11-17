package com.mrone.feignclients;

import com.mrone.commons.Result;
import com.mrone.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "USER")
public interface UserClient {

    @PostMapping("/feign/byall")
    List<UserDto> byAll();

    @PostMapping("/feign/bynumid")
    Result<Object> byNumId(int id);
}
