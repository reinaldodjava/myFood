package com.myFood.order.application.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author reinaldo.locatelli
 */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<Map> handleUserNotFoundException(CustomException ex, WebRequest request) {

        Map map = new HashMap();

        if (ex.getObjectError() != null) {
            map.put("object", ex.getObjectError());
        }
        
        if (ex.getMessage() != null) {
            map.put("message", ex.getMessage());
        }
        
        map.put("path", ((ServletWebRequest) request).getRequest().getServletPath());
        map.put("method", ((ServletWebRequest) request).getRequest().getMethod());
        map.put("timestamp", ex.getTimestamp());

        return new ResponseEntity<>(map, ex.getStatus());
    }

}
