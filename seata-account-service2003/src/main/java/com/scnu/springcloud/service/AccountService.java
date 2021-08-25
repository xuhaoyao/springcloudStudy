package com.scnu.springcloud.service;

import java.math.BigDecimal;

public interface AccountService {

    int update(Long userId, BigDecimal money);

}
