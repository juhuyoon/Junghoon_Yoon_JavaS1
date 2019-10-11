package com.company.WeatherAPI.Controllers;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {NumberFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<VndErrors.VndError> numberFormatExceptionHandler(NumberFormatException e, WebRequest request) {
        VndErrors.VndError clientErr = new VndErrors.VndError(e.toString(), "Invalid zipcode entered");
        ResponseEntity<VndErrors.VndError> responseEntity = new ResponseEntity<>(clientErr, HttpStatus.UNPROCESSABLE_ENTITY);

        return responseEntity;
    }
}
