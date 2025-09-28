package com.acxiom_tech_service.dto;

public class ApiResponseDto<T> {

    private String status;      // "success" or "error"
    private String message;     // brief message
    private String details;     // optional details
    private T data;             // response data
    private int statusCode;     // HTTP status code

    public ApiResponseDto() {
    }

    public ApiResponseDto(String status, String message, String details, T data, int statusCode) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.data = data;
        this.statusCode = statusCode;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    // Static helper methods for convenience
    public static <T> ApiResponseDto<T> success(String message, T data, int statusCode) {
        return new ApiResponseDto<>("success", message, null, data, statusCode);
    }

    public static <T> ApiResponseDto<T> error(String message, String details, int statusCode) {
        return new ApiResponseDto<>("error", message, details, null, statusCode);
    }
}

