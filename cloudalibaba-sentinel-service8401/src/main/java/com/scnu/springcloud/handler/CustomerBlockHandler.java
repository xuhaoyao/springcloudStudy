package com.scnu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.scnu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {

    public static CommonResult customerBlockMethod(BlockException exception){
        return new CommonResult(500,"000-->限流处理-->" + exception.getClass().getCanonicalName());
    }

    public static CommonResult customerBlockMethod1(BlockException exception){
        return new CommonResult(500,"111-->限流处理-->" + exception.getClass().getCanonicalName());
    }

}
