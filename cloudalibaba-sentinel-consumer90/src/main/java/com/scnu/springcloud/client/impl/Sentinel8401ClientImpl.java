package com.scnu.springcloud.client.impl;

import com.scnu.springcloud.client.Sentinel8401Client;
import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class Sentinel8401ClientImpl implements Sentinel8401Client {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(50001,"openFeign fallback...");
    }
}
