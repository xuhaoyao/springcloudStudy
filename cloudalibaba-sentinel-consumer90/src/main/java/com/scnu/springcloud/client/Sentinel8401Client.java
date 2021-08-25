package com.scnu.springcloud.client;

import com.scnu.springcloud.client.impl.Sentinel8401ClientImpl;
import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = Sentinel8401ClientImpl.class)
@Component
public interface Sentinel8401Client {

    @GetMapping(value = "/paymentSQL/{id}")
    CommonResult<Payment> paymentSQL(@PathVariable("id") Long id);

}
