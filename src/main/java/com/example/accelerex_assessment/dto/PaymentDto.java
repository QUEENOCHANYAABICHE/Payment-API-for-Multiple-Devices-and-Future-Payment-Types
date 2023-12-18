package com.example.accelerex_assessment.dto;

import com.example.accelerex_assessment.enums.Device;
import com.example.accelerex_assessment.enums.PaymentType;
import lombok.Data;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

@Data
public class PaymentDto {
    @NotNull
    @Pattern(value = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
    private String email;
    @NotNull
    private String amount;
    private String reference;
    private String callbackUrl;
    private PaymentType paymentType;
    private Device device;
}
