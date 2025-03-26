package com.seejang.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import static com.seejang.common.Result.SUCCESS;

@Getter
@Builder
@ToString
public class DefaultResponse<T> {

    private Result result;

    private T data;

    public static <T> DefaultResponse<T> successful() {
        return DefaultResponse.<T>builder().result(SUCCESS).build();
    }

    public static <T> DefaultResponse<T> successful(T data) {
        return DefaultResponse.<T>builder()
                .result(SUCCESS)
                .data(data)
                .build();
    }
}
