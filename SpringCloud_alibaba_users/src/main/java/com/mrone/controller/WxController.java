package com.mrone.controller;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import com.mrone.service.UserService;
import com.mrone.service.WxMessageService;
import com.mrone.util.RedisUtil;
import com.mrone.vo.InMessage;
import com.mrone.vo.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/19 17:52
 * @Description:
 */
@Controller
@ResponseBody
@RequestMapping("/wx")
public class WxController {

    // 微信页面填写的token，必须保密
    private static final String TOKEN = "";

    private static final String APPID = "";

    private static final String APPSECRET = "";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WxMessageService wxMessageService;

    /**
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return  微信公众号接口请求 认证
     */
    @GetMapping("/back")
        public String test(String signature, String timestamp, String nonce, String echostr) {
        // 1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp, nonce, TOKEN};
        Arrays.sort(arr);
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        // 这里利用了hutool的加密工具类
        String sha1 = SecureUtil.sha1(sb.toString());
        // 3. 加密后的字符串与signature对比，如果相同则该请求来源于微信，原样返回echostr
        if (sha1.equals(signature)) {
            return echostr;
        }
        // 接入失败
        return null;
    }

    /**
     *
     * @param inMessage
     * @return 消息回调
     */
    @PostMapping(value = "/back",produces ="application/xml;charset=UTF-8")
    public Object handleMessage(@RequestBody InMessage inMessage){
        OutMessage outMessage = wxMessageService.handleWxMessage(inMessage);
        return outMessage;
    }


    @GetMapping("/getAccessToken")
    public String getAccessToken(){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID +
                "&secret=" + APPSECRET;
        // 利用hutool的http工具类请求获取access_token
        String result = HttpUtil.get(url);
        // 将结果解析为json
        JSONObject jsonObject = JSONUtil.parseObj(result);
        // 获取access_token
        String accessToken = jsonObject.getStr("access_token");
        if (!StringUtils.isEmpty(accessToken)){
            // 将access_token存入redis
            redisUtil.set("access_token", accessToken);
        }
        return accessToken;
    }

    @GetMapping("/createMenu")
    public String createMenu(){
        // 从redis中取出access_token
        String accessToken = String.valueOf(redisUtil.get("access_token"));
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        // 创建菜单的请求体
        String body = "{\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"位置\",\n" +
                "          \"key\":\"button_location\"\n" +
                "      }]}";
        return HttpUtil.post(url, body);
    }
}
