package com.scnu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.scnu.springcloud.entities.CommonResult;

public class CustomBlockHandler {

    public static CommonResult customerBlockMethod(Long id,BlockException exception){
        return new CommonResult(500,"000-->限流处理-->" + exception.getMessage());
    }

}
