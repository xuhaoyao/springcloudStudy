package com.scnu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {

    int update(@Param("userId") Long userId, @Param("money") BigDecimal money);

}
