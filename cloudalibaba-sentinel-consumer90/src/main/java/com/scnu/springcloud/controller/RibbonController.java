package com.scnu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import com.scnu.springcloud.handler.CustomBlockHandler;
import com.scnu.springcloud.handler.CustomFallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.provider-payment}")
    private String paymentUrl;

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    @SentinelResource(value = "paymentSQL",
            blockHandlerClass = CustomBlockHandler.class,blockHandler = "customerBlockMethod",  //管控制台的限流
            fallbackClass = CustomFallbackHandler.class,fallback = "customerFallbackMethod")    //管java代码的异常
    public CommonResult paymentSQL(@PathVariable("id") Long id){
        CommonResult result = restTemplate.getForObject(paymentUrl + "/paymentSQL/" + id, CommonResult.class);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

}
