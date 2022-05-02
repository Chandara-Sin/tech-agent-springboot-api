package com.scd.tech_agent.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String path;
    private int status_code;
    private String error;

    public ErrorDetails(Date timestamp, String message, String path, int status, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status_code = status;
        this.error = error;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public int getStatus() {
        return status_code;
    }

    public String getError() {
        return error;
    }
}
