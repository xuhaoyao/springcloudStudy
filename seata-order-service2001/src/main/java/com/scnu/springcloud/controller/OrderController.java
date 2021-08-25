package com.scnu.springcloud.controller;

import com.scnu.springcloud.domain.Order;
import com.scnu.springcloud.entities.CommonResult;
import com.scnu.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/save")
    public CommonResult saveOrder(Order order){
        orderService.create(order);
        return new CommonResult(200,"生成订单成功!");
    }

}
