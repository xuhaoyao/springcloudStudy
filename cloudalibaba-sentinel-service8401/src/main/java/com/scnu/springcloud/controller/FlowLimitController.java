package com.scnu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class FlowLimitController
{

    private int count = 0;

    @GetMapping("/testA")
    public String testA() {
        log.info(Thread.currentThread().getName() + "--->" + "testA");
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
//        if(Math.random() > 0.3) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        count++;
        if(count !=  3) {
            int a = 1 / 0;
        }
        return "------testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "block_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "testHotKey....";
    }

    public String block_testHotKey(String p1, String p2, BlockException blockException){
        return "block_testHotKey...";
    }


}