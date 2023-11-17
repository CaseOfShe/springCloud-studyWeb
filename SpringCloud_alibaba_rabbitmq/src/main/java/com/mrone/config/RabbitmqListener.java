package com.mrone.config;

import com.alibaba.fastjson.JSON;
import com.mrone.dto.StarDto;
import com.mrone.util.RedisUtil;
import com.mrone.vo.CanalMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @program: studyweb
 * @description:
 * @author: Mr.One
 * @create: 2022-09-10 15:50
 **/
@Component
public class RabbitmqListener {

    @Autowired
    private RedisUtil redisUtil;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "binlog"), exchange = @Exchange(value = "mysql"),key = "mysql-binlog"))
    public void businessQueue(@Payload byte[] message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        try {
            // canal发送到rabbitmq的消息默认为二进制字节流，无法看懂，所以将二进制字节流转换为String类型
            String realMessage = new String(message, StandardCharsets.UTF_8);
            // 将String转换为对象类型
            CanalMessage<StarDto> canalMessage = JSON.parseObject(realMessage, CanalMessage.class);
            // 只针对studyweb数据库中的user表
            if ("studyweb".equals(canalMessage.getDatabase()) && "star".equals(canalMessage.getTable())) {
                if ("UPDATE".equals(canalMessage.getType()) || "INSERT".equals(canalMessage.getType())) {
                    // StarList不能直接等于canalMessage.getData()，否则会出现类型无法转换问题
                    List<StarDto> StarList = JSON.parseArray(JSON.parseObject(realMessage).getString("data"), StarDto.class);
                    for (StarDto star : StarList) {
                        logger.info(star.toString());
                        redisUtil.set("star::" + star.getAid(), star.getUid());
                    }
                } else if ("DELETE".equals(canalMessage.getType())) {
                    List<StarDto> StarList = JSON.parseArray(JSON.parseObject(realMessage).getString("data"), StarDto.class);
                    for (StarDto star : StarList) {
                        redisUtil.del("star::" + star.getAid());
                    }
                }
            }
            // 手动ack,确认消息已被消费
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            // requeue=false 表示被拒绝的消息进入死信队列
            channel.basicNack(deliveryTag, false, false);
            e.printStackTrace();
        }
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "dead.letter.queue"), exchange = @Exchange(value = "dead.letter.exchange")))
    public void deadLetterQueue(@Payload byte[] message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) {
        logger.info("死信队列业务逻辑");
    }
}
