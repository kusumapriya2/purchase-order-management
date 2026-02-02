package com.pomanagement.purchaseordermanagement.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    public ApiResponse(boolean success, String message, T data) {
        this.status = success ? "SUCCESS" : "FAILED";
        this.message = message;
        this.data = data;
    }


    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, data);
    }

    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>("FAILED", message, null);
    }
}
