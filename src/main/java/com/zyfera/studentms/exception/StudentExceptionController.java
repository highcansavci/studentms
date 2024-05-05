package com.zyfera.studentms.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class StudentExceptionController {
    private static final Logger log = LoggerFactory.getLogger(StudentExceptionController.class);

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, List<String>>> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("Constraints of the entities are not met!");
        List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).toList();
        return new ResponseEntity<>(buildValidationErrors(errors), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMalformedBodyException(HttpMessageNotReadableException ex) {
        log.error("Required student body is malformed!");
        return new ResponseEntity<>("Required student body is malformed!", HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> buildValidationErrors(List<String> errors) {
        Map<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("errors", errors);
        return errorMap;
    }

}

