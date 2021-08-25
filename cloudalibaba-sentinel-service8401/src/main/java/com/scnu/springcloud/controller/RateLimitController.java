package com.scnu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.entities.Payment;
import com.scnu.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/testResource")
    @SentinelResource(value = "testResource",blockHandler = "block_testResource")
    public CommonResult testResource(){
        return new CommonResult(200,"QPS在限定范围内",null);
    }

    public CommonResult block_testResource(BlockException blockException){
        return new CommonResult(500,"QPS超出阈值--->" + blockException.getClass().getCanonicalName(),null);
    }

    /**
     * 此处要按资源名称byUrl,限流之后才能进入自定义的customerBlockMethod1
     *
     * 若按/rateLimit/byUrl在控制台配置限流,那么我们自定义的方法不起效
     *
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "customerBlockMethod1")
    public CommonResult byUrl()
    {
        return new CommonResult(200,"按url限流测试OK",new Payment(2020L,"serial002"));
    }

}
