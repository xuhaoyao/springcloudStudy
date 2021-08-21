package com.scnu.springcloud.controller;

import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import com.scnu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果:{}",result);
        if(result > 0){
            return new CommonResult(200,"订单支付成功,serverPort:" + serverPort,result);
        }
        else{
            return new CommonResult(500,"订单支付失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:{}",payment);
        if(payment != null){
            return new CommonResult(200,"查询成功,serverPort:" + serverPort,payment);
        }
        else{
            return new CommonResult(500,"没有对应记录,查询失败",null);
        }
    }

    @GetMapping("/payment/discovery")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(System.out::println);
        List<ServiceInstance> instances = discoveryClient.getInstances(services.get(0));
        instances.forEach(System.out::println);
        return new CommonResult(200,"测试discovery",discoveryClient);
    }

    @GetMapping("/payment/lb")
    public String myLb(){
        return serverPort;
    }


    @GetMapping("/payment/feign/timeout")
    public String timeout() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
