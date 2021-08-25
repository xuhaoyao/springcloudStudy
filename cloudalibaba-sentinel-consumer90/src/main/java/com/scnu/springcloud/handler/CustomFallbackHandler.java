package com.scnu.springcloud.handler;

import com.scnu.springcloud.entities.CommonResult;

public class CustomFallbackHandler {

    public static CommonResult customerFallbackMethod(Long id,Throwable e){
        return new CommonResult(501,"000-->降级处理-->" + e.getMessage());
    }

}
