package com.example.accelerex_assessment.service;

import com.example.accelerex_assessment.dto.PaymentDto;
import com.example.accelerex_assessment.model.Payment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {
    Object initializePayment(PaymentDto paymentDto);

    Object verifyPayment(String reference);

    List<Payment> retrievePayments();
}
