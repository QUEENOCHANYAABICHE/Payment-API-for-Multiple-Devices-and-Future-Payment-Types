package com.example.accelerex_assessment.service.impl;

import com.example.accelerex_assessment.api.initializePayment.InitializePaymentResponse;
import com.example.accelerex_assessment.api.verifyPayment.VerifyPaymentResponse;
import com.example.accelerex_assessment.dto.PaymentDto;
import com.example.accelerex_assessment.dto.PaymentRequestDto;
import com.example.accelerex_assessment.enums.PaymentType;
import com.example.accelerex_assessment.enums.Status;
import com.example.accelerex_assessment.exception.ResourceNotFound;
import com.example.accelerex_assessment.model.Payment;
import com.example.accelerex_assessment.repository.PaymentRepository;
import com.example.accelerex_assessment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.example.accelerex_assessment.util.Constants.CALL_BACK_URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.paystack.co")
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer sk_test_61af4a1f2bfe3f207f176b6a635602bc0e47af7f")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    @Override
    public Object initializePayment(PaymentDto paymentDto) {
        String reference = UUID.randomUUID().toString();

        Payment payment = mapPaymentDtoToEntity(paymentDto);
        payment.setReference(reference);
        payment.setCallbackUrl(CALL_BACK_URL);

        InitializePaymentResponse initializePaymentResponse = new InitializePaymentResponse();
        if (paymentDto.getPaymentType().equals(PaymentType.CARD))  {

            List<String> channels = List.of("card");

            PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                    .amount(paymentDto.getAmount())
                    .email(paymentDto.getEmail())
                    .reference(reference)
                    .callbackUrl(CALL_BACK_URL)
                    .channels(channels)
                    .build();

            initializePaymentResponse = webClient
                    .post()
                    .uri("/transaction/initialize")
                    .bodyValue(paymentRequestDto)
                    .retrieve()
                    .bodyToMono(InitializePaymentResponse.class)
                    .block();

            if (initializePaymentResponse != null) {
                if (initializePaymentResponse.getStatus()) {
                    payment.setStatus(Status.PENDING);
                    paymentRepository.save(payment);
                } else {
                    payment.setStatus(Status.FAILED);
                    paymentRepository.save(payment);
                }
            }
        }

        return initializePaymentResponse;
    }

    @Override
    public Object verifyPayment(String reference) {
        Payment payment = paymentRepository
                .findByReference(reference)
                .orElseThrow(() ->
                        new ResourceNotFound("Transaction with the reference: " + reference + " not found"));

        VerifyPaymentResponse verifyPaymentResponse = webClient
                .get()
                .uri("/transaction/verify/" + reference)
                .retrieve()
                .bodyToMono(VerifyPaymentResponse.class)
                .block();

        log.info("{}", verifyPaymentResponse);

        if (verifyPaymentResponse != null) {
            if (verifyPaymentResponse.getStatus()) {
                payment.setStatus(Status.SUCCESS);
                paymentRepository.save(payment);
                log.info("{}", payment.getStatus());
            } else {
                payment.setStatus(Status.FAILED);
                paymentRepository.save(payment);
            }
        }
        return verifyPaymentResponse;
    }

    @Override
    public List<Payment> retrievePayments() {
        return paymentRepository.findAll();
    }

    public Payment mapPaymentDtoToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setAmount(paymentDto.getAmount());
        payment.setEmail(paymentDto.getEmail());
        payment.setPaymentType(paymentDto.getPaymentType());
        payment.setDevice(paymentDto.getDevice());
        return payment;
    }
}
