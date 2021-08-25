package com.scnu.springcloud.dao;

import com.scnu.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    int save(Order order);

    int update(@Param("userId") Long userId,@Param("status") Integer status);

}
