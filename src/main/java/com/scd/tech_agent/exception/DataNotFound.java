
package com.scd.tech_agent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataNotFound extends Exception {

    public DataNotFound(String message) {
        super(message);
    }
}