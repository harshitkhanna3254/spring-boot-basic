package com.hk.restfulwebservice.exception;

import com.hk.restfulwebservice.constants.Constants;
import com.hk.restfulwebservice.exception.model.GenericExceptionResponse;
import com.hk.restfulwebservice.exception.types.UserAlreadyPresentException;
import com.hk.restfulwebservice.exception.types.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Controller
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        GenericExceptionResponse exceptionResponse =
                new GenericExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request) {
        GenericExceptionResponse exceptionResponse =
                new GenericExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyPresentException.class)
    public final ResponseEntity<Object> handleUserAlreadyPresentExceptions(UserNotFoundException ex, WebRequest request) {
        GenericExceptionResponse exceptionResponse =
                new GenericExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        GenericExceptionResponse exceptionResponse =
//                new GenericExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
//
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericExceptionResponse exceptionResponse =
                new GenericExceptionResponse(new Date(), Constants.VALIDATION_FAILED, ex.getBindingResult().toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
