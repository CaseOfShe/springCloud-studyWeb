package com.mrone.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.mrone.commons.Verse;
import com.mrone.service.UserService;
import com.mrone.service.WxMessageService;
import com.mrone.util.RedisUtil;
import com.mrone.vo.InMessage;
import com.mrone.vo.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/19 17:44
 * @Description:
 */
@Service
public class WxMessageServiceImpl implements WxMessageService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private UserService userService;




    @Override
    public OutMessage handleWxMessage(InMessage inMessage) {
        // 创建响应消息实体对象
        OutMessage outMessage = new OutMessage();
        // 把原来的接收方设置为发送方
        outMessage.setFromUserName(inMessage.getToUserName());
        // 把原来的发送方设置为接收方
        outMessage.setToUserName(inMessage.getFromUserName());
        //将openid 取出来存入redis里面
        String openid = inMessage.getFromUserName();
        redisUtil.set("openid",openid);
        // 设置消息类型
        outMessage.setMsgType(inMessage.getMsgType());
        // 设置消息时间
        outMessage.setCreateTime(System.currentTimeMillis() / 1000);
        // 根据接收到消息类型，响应对应的消息内容
        if ("text".equals(inMessage.getMsgType())){
            // 根据不同的关键字回复消息
            String inContent = inMessage.getContent();
            if (inContent.contains("学习")){
                Verse verse = Verse.randomType(Verse.values());
                outMessage.setContent(verse.getVerse());
            }else if (inContent.contains("验证码")||inContent.contains("登录")){
                Verse verse = Verse.randomType(Verse.values());
                String code = RandomUtil.randomString(6);
                redisUtil.set("code" + code, code,60*5);
                outMessage.setContent(verse.getVerse()+"\n\n" +"你的五分钟验证码是！" +code);
            }else {
                outMessage.setContent(inContent);
            }
        }else if ("image".equals(inMessage.getMsgType())){
            // 图片
            outMessage.setMediaId(new String[]{inMessage.getMediaId()});
        }else if("subscribe".equals(inMessage.getEvent())){
            //回复
            String code = RandomUtil.randomString(6);
            redisUtil.set("code" + code, code,60*5);
            outMessage.setMsgType("text");
            outMessage.setContent("感谢你的关注，祝你有个好心情！\n\n"+"验证码:！" +code);
        }else if("unsubscribe".equals(inMessage.getEvent())){
            userService.delByOpenid();
        }
        return outMessage;
    }
}
