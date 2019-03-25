package com.myFood.order.application.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;

/**
 *
 * @author reinaldo.locatelli
 */
public class CustomException extends RuntimeException {

    private Object objectError;
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private long timestamp = new Date().getTime();
    private String message;

    public CustomException(String messageKey, HttpStatus status) {
        super(messageKey);
        setMessage(messageKey);
        this.status = status;
    }

    public CustomException(String messageKey, Object objectError, HttpStatus status) {
        super(messageKey);
        setMessage(messageKey);
        this.status = status;
        this.objectError = objectError;
    }

    public CustomException(Object objectError, HttpStatus status) {
        this.objectError = objectError;
        this.status = status;
    }

    @Override
    public String getMessage() {
        if (message != null && !message.isEmpty()) {
            return message;
        }
        return super.getMessage();
    }

    public Object getObjectError() {
        return objectError;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    private void setMessage(String message) {
        this.message = message;

    }

}
