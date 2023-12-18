package com.example.accelerex_assessment.model;

import com.example.accelerex_assessment.enums.Device;
import com.example.accelerex_assessment.enums.PaymentType;
import com.example.accelerex_assessment.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "transaction")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends AuditBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "amount", nullable = false)
    private String amount;
    @Column(name = "reference", nullable = false)
    private String reference;
    @Column(name = "callback_url")
    private String callbackUrl;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "payment_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(name = "device")
    @Enumerated(EnumType.STRING)
    private Device device;

}
