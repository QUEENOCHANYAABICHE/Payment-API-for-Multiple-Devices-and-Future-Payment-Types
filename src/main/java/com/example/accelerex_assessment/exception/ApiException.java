package com.example.accelerex_assessment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class ApiException {
    private String message;
    private Throwable throwable;
    private HttpStatus status;
    private ZonedDateTime timestamp;
}
