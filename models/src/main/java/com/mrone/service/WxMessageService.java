package com.mrone.service;

import com.mrone.vo.InMessage;
import com.mrone.vo.OutMessage;

/**
 * Created 一字先生
 *
 * @Author: Mr.One
 * @Date: 2022/06/19 17:44
 * @Description:
 */
public interface WxMessageService {
    OutMessage handleWxMessage(InMessage inMessage);
}
