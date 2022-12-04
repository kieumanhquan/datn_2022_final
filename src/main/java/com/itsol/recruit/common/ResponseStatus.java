package com.itsol.recruit.common;

import org.springframework.http.HttpStatus;

public enum ResponseStatus {
    SUCCESS(HttpStatus.OK, "Success"),
    CREATED(HttpStatus.CREATED, "Created"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST,"Bad request!"),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"Email đã tồn tại!"),
    USERNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"Tên đăng nhập đã tồn tại!"),
    PHONE_NUMBER_ALREADY_EXIST(HttpStatus.BAD_REQUEST,"Số điện thoại đã tồn tại !"),
    CANDIDATE_NOT_REGISTERED(HttpStatus.BAD_REQUEST,"Candidate not registered!"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized!"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!");

    private HttpStatus status;
    private String message;

    ResponseStatus(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
