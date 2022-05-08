package com.scd.tech_agent.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorDetails {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private String message;
    private String path;
    private int status_code;
    private String error;

    public ErrorDetails(LocalDateTime timestamp, String message, String path, int status, String error) {
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
        this.status_code = status;
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
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
