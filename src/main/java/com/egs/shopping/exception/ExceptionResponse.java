package com.egs.shopping.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {
    private String requestTime;
    private String message;
    private String requestMethod;
    private String uri;
    private Locale locale;

}