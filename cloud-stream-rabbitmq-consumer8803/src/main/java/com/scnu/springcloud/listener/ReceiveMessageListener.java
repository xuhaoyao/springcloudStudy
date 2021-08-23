package com.scnu.springcloud.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 默认情况下:
 *  会产生从重复消费,因为8802和8803所在的mq队列是不一样的,被认为是不同的组,消息由交换机发出的时候,默认发给所有队列,导致了重复消费
 *
 * 解决:
 *  yml下面配置group,将8802和8803至于同一个group,解决问题,默认采用轮询的方式拿消息
 *  设置了group,默认消息也持久化
 *
 */
@EnableBinding(Sink.class)
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void receiveMessage(Message<String> message){
        System.out.println("消费者端口号:" + serverPort + ",消费" + message.getPayload());
    }
}
