package com.hk.restfulwebservice.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GenericExceptionResponse {
    Date timestamp;
    String message;
    String details;
}
