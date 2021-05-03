package com.intigral.weatherapi.config;

import com.intigral.weatherapi.dto.Error;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public List<Error> handleBindException(BindException e) {

        log.info("Validation exception: ", e);

        List<Error> errors = new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = null;
            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            }
            errors.add(Error.builder()
                    .fieldName(fieldName)
                    .message(error.getDefaultMessage())
                    .build());
        });
        return errors;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = FeignException.class)
    public String handleFeignException(FeignException e) {

        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e) {

        return e.getMessage();
    }

}
