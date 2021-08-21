package com.scnu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id：  "+id+"\t"+"哈哈哈"  ;
    }

    @HystrixCommand(fallbackMethod = "fallback_paymentInfo_TimeOut",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")  //3秒钟以内就是正常的业务逻辑
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 2;
        //int a = 10 / 0;
        try { TimeUnit.SECONDS.sleep(timeNumber); }catch (Exception e) {e.printStackTrace();}
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_TimeOut,id：  "+id+"\t"+"呜呜呜"+" 耗时(秒)"+timeNumber;
    }

    public String fallback_paymentInfo_TimeOut(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   fallback_paymentInfo_TimeOut,id：  "
                +id+"\t"+"呜呜呜"+" 服务器繁忙...";

    }

    //================================服务熔断
    //在HystrixCommandProperties可以找到这些配置
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }

    /**
     * 测试的效果:
     * 10个请求中如果有60%失败,断路器开启,此时调用正确参数出现下面结果
     *      id 不能负数，请稍候再试,(┬＿┬)/~~ id: 2
     *  这时候就是服务熔断
     *  这个时候进入休眠时间期,所有请求都理解为失败逻辑,休眠过后(sleepWindowInMilliseconds),即10s后
     *  进入半开状态
     *  这个时候如果有一个正确请求,那么断路器close,否则继续open,重置休眠时间期
     */
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}
 