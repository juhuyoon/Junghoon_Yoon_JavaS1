package com.company.HelloWebServices.Controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public ResponseEntity<VndErrors> webServiceInputValidationError(IllegalArgumentException e, WebRequest request) {
        VndErrors err = new VndErrors(request.toString(), e.getMessage());
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }
}
