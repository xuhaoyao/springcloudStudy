package com.scnu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class HystrixCallback implements PaymentFeignService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "HystrixCallback->服务器繁忙,请稍后再试试..." + Thread.currentThread().getName();
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "HystrixCallback->服务器繁忙,请稍后再试试..." + Thread.currentThread().getName();
    }
}
