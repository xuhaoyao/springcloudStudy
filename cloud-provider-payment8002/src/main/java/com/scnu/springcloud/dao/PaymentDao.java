package com.scnu.springcloud.dao;

import com.scnu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(Long id);

}
