package com.company.JunghoonYoonU1Capstone.Controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@RequestMapping(produces = "appication/vnd.error+json")
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public ResponseEntity<VndErrors> outOfRangeException(IllegalArgumentException e, WebRequest webRequest) {
        VndErrors errors = new VndErrors(webRequest.toString(), e.getMessage());
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<VndErrors> notEnoughValues(MethodArgumentNotValidException e, WebRequest request) {

        BindingResult result = e.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        List<VndErrors.VndError> vndErrorList = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            VndErrors.VndError vndError = new VndErrors.VndError(request.toString(), fieldError.getDefaultMessage());
            vndErrorList.add(vndError);
        }
        VndErrors errors = new VndErrors(vndErrorList);

        return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
