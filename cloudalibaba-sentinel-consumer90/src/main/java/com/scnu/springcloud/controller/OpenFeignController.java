package com.scnu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.scnu.springcloud.client.Sentinel8401Client;
import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import com.scnu.springcloud.handler.CustomBlockHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OpenFeignController {

    @Autowired
    private Sentinel8401Client sentinel8401Client;


    @GetMapping(value = "/openfeign/paymentSQL/{id}")
    @SentinelResource(value = "openfeignResource",
            blockHandlerClass = CustomBlockHandler.class,blockHandler = "customerBlockMethod")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        CommonResult result = sentinel8401Client.paymentSQL(id);
        return result;
    }

}