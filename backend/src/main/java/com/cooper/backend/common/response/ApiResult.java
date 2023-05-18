package com.cooper.backend.common.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResult<T> {

    private final T data;
    private final String message;

    @SuppressWarnings("unchecked")
    public static <T> ApiResult<T> success(T data) {
        return (ApiResult<T>) ApiResult.builder()
                .data(data)
                .build();
    }

    @SuppressWarnings("unchecked")
    public static <T> ApiResult<T> fail(String message) {
        return (ApiResult<T>) ApiResult.builder()
                .message(message)
                .build();
    }

}
