package com.scnu.springcloud.service.impl;

import com.scnu.springcloud.dao.OrderDao;
import com.scnu.springcloud.domain.Order;
import com.scnu.springcloud.service.AccountService;
import com.scnu.springcloud.service.OrderService;
import com.scnu.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    //下订单->减库存->扣余额->改（订单）状态
    @GlobalTransactional(rollbackFor = Exception.class)    //解决分布式数据库的事务提交回滚
    @Transactional
    @Override
    public boolean create(Order order) {
        log.info("----->开始生成订单");
        orderDao.save(order);
        log.info("----->订单生成完毕,库存相应地需要减少");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("----->减库存后,扣除用户余额");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->用户余额扣除后,订单基本完成,此时修改订单状态");
        orderDao.update(order.getUserId(),1);
        return true;
    }

}
