package com.scnu.springcloud.controller;

import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import com.scnu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


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

    @GetMapping("/payment/lb")
    public String myLb(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String timeout() {
        //这里测试不超时,测试默认的负载均衡算法
        //可以发现,当第一次连接超时过后,若有其他服务器可以尝试连接,那么会将请求转发给另一个服务器,
        // 下次再发送请求,不会发给超时的那一个(8001),而是一直发给8002(不超时)
        return serverPort;
    }

}
