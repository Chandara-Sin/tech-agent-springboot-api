package com.scd.tech_agent.exception;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

public class ErrorResponse {
    public static Object responseGeneral(Object resDetails, Integer statusCode) {
        var res = new HashMap<String, Object>();
        res.put("status", statusCode);
        res.put("error", resDetails);
        return res;
    }
}
