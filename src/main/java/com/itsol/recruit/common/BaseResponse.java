package com.itsol.recruit.common;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@JsonIgnoreProperties({"status"})
//@Builder
public class BaseResponse<T> implements Serializable {
    private T data;
//    private HttpStatus status;
    private int statusCode;
    private String message;

    public BaseResponse(T data, HttpStatus status, String message) {
        this.data = data;
//        this.status = status;
        this.statusCode = status.value();
        this.message = message;
    }
    public static <T> BaseResponse<T> onOk() {
        ResponseStatus status = ResponseStatus.SUCCESS;
        return new BaseResponse<>(null, status.getStatus(), status.getMessage());
    }

    public static <T> BaseResponse<T> onOk(T data) {
        ResponseStatus status = ResponseStatus.SUCCESS;
        return new BaseResponse<>(data, status.getStatus(), status.getMessage());
    }

    public static <T> BaseResponse<T> onCreated() {
        ResponseStatus status = ResponseStatus.CREATED;
        return new BaseResponse<>(null, status.getStatus(), status.getMessage());
    }

    public static <T> BaseResponse<T> onCreated(T data) {
        ResponseStatus status = ResponseStatus.CREATED;
        return new BaseResponse<>(data, status.getStatus(), status.getMessage());
    }

    public static <T> BaseResponse<T> onError(ResponseStatus status) {
        return new BaseResponse<>(null, status.getStatus(), status.getMessage());
    }
    public static <T> BaseResponse<T> onError(ResponseStatus status, T data) {
        return new BaseResponse<>(data, status.getStatus(), status.getMessage());
    }
    public static <T> BaseResponse<T> onUnauthorizedError() {
        ResponseStatus status = ResponseStatus.UNAUTHORIZED;
        return new BaseResponse<>(null, status.getStatus(), status.getMessage());
    }

    public static <T> BaseResponse<T> onInternalServerError() {
        ResponseStatus code = ResponseStatus.INTERNAL_SERVER_ERROR;
        return new BaseResponse<>(null, code.getStatus(), code.getMessage());
    }
}
