package com.scnu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.scnu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局的降级方法,对应的方法参数列表可以不匹配,但是精确的降级方法,参数必须一致,否则报错
 */
@RestController
@DefaultProperties(defaultFallback = "fallback_globalMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentFeignService.paymentInfo_OK(id);
    }

//    @HystrixCommand(fallbackMethod = "fallback_paymentInfo_TimeOut",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")  //1.5秒钟以内就是正常的业务逻辑
//    })
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        //int a = 10 / 0;
        return paymentFeignService.paymentInfo_TimeOut(id);
    }

    public String fallback_paymentInfo_TimeOut(@PathVariable("id") Integer id){
        return id + ",服务器繁忙,请稍后再试试..." + Thread.currentThread().getName();
    }

    public String fallback_globalMethod(){
        return "服务器繁忙,请稍后再试试..." + Thread.currentThread().getName();
    }

}
