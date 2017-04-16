package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

@Component
public class RetryExpressionHelper {

    // exceptionExpressionでは、exceptionが引数で受け取れる
    public boolean shouldRetry(Throwable t) {
        return t instanceof HttpServerErrorException
               && ((HttpServerErrorException) t).getRawStatusCode() == HttpStatus.GATEWAY_TIMEOUT.value();
    }
}