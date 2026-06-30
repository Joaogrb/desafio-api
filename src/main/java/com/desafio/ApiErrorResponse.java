package com.desafio;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "error", "errorMessage", "fieldErrors", "timestamp" })
public class ApiErrorResponse {
	
    private int status;
    private String error;
    private String errorMessage;
    private Map<String, String> fieldErrors;
	private LocalDateTime timestamp;

    public ApiErrorResponse(int status, String error, Map<String, String> fieldErrors) {
        this.status = status;
        this.error = error;
        this.fieldErrors = fieldErrors;
        this.timestamp = LocalDateTime.now();
    }
    
    public ApiErrorResponse(int status, String error, String errorMessage) {
        this.status = status;
        this.error = error;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getErrorMessage() { return errorMessage;}
    public LocalDateTime getTimestamp() { return timestamp; }


	public Map<String, String> getFieldErrors() { return fieldErrors; }

}
