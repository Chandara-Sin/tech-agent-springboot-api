package com.scd.tech_agent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataInvalid extends Exception {

    public DataInvalid(String message) {
        super(message);
    }
}
