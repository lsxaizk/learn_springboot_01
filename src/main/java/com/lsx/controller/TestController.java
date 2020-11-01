package com.lsx.controller;

import com.lsx.config.RabbitConfig;
import com.lsx.rabiitmqtest.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lsxaizk
 * @Date: 2020/8/10 21:48
 */
@RestController
@RequestMapping("Test")
public class TestController {
    @Autowired
    private RabbitConfig rabbitConfig;
    @GetMapping("/sendMsg")
    public String sendMsg(String content){
        MsgProducer msgProducer=new MsgProducer(rabbitConfig.rabbitTemplate());
        msgProducer.sendMsg(content);
        return "success";
    }
}
