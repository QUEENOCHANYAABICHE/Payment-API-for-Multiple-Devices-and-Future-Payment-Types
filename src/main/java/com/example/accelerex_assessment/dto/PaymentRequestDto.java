package com.example.accelerex_assessment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class PaymentRequestDto {
    private String email;
    private String amount;
    private String reference;
    private String callbackUrl;
    private List<String> channels;
}
