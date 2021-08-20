package com.scnu.springcloud.service;

import com.scnu.springcloud.entities.Payment;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
