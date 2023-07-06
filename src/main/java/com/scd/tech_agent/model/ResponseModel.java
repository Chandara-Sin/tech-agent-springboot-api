package com.scd.tech_agent.model;

import java.util.HashMap;

public class ResponseModel {
    public static Object responseGeneral(Object resDetails, Integer statusCode) {
        var res = new HashMap<String, Object>();
        res.put("statusCode", statusCode);
        res.put("error", resDetails);
        return res;
    }

    public static Object errorResponse(Object resDetails, Integer statusCode) {
        var res = new HashMap<String, Object>();
        res.put("statusCode", statusCode);
        res.put("data", resDetails);
        return res;
    }
}
