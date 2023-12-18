package com.example.accelerex_assessment.contorller;

import com.example.accelerex_assessment.dto.PaymentDto;
import com.example.accelerex_assessment.model.Payment;
import com.example.accelerex_assessment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accelerex")
public class PaymentController {
    private final PaymentService paymentServiceImpl;

    @PostMapping("initialize-payment")
    public ResponseEntity<Object> makePayment(@Valid @RequestBody PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentServiceImpl.initializePayment(paymentDto));
    }

    @GetMapping("verify-payment")
    public ResponseEntity<Object> verifyPayment(@RequestParam String trxref) {
        return ResponseEntity.ok(paymentServiceImpl.verifyPayment(trxref));
    }

    @GetMapping("get-payments")
    public ResponseEntity<List<Payment>> getPayment() {
        return ResponseEntity.ok(paymentServiceImpl.retrievePayments());
    }

}
