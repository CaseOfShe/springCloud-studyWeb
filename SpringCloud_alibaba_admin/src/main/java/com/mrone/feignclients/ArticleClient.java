package com.mrone.feignclients;

import com.mrone.commons.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-08 19:12
 **/
@FeignClient(value = "ARTICLE")
public interface ArticleClient {
    @PostMapping("/feign/farticle")
    Result<Object> feignAdminArticle(int id);

    @PostMapping("/feign/fcomment")
    Result<Object> feignAdminFComment(@RequestParam("aid") int aid,@RequestParam("fid") int fid);

    @PostMapping("/feign/fccomment")
    Result<Object> feignAdminCComment(@RequestParam("aid")int uid,@RequestParam("cid")int cid);

}
