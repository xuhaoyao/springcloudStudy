package com.scnu.springcloud.service.impl;

import com.scnu.springcloud.dao.AccountDao;
import com.scnu.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public int update(Long userId, BigDecimal money) {
        //TODO 此处还需要判断钱是否足够

        //模拟异常
        int a = 10 / 0;
        return accountDao.update(userId,money);
    }
}
