package com.axonactive.roomLeaseManagement.exception;

import org.springframework.http.HttpStatus;

public class ExceptionList {
    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static final String ASSET_NOT_FOUND_MSG_KEY = "AssetNotExisted";
    private static final String ASSET_NOT_FOUND_MSG = "Asset Not Found";

    public static ResponseException AssetNotFound() {
        return notFound(ASSET_NOT_FOUND_MSG_KEY, ASSET_NOT_FOUND_MSG);
    }

//    private static final String CONTRACTINFO_NOT_FOUND_MSG_KEY = "Contract infoNotExisted";
//    private static final String ASSET_NOT_FOUND_MSG = "Asset Not Found";
//
//    public static ResponseException AssetNotFound() {
//        return notFound(ASSET_NOT_FOUND_MSG_KEY, ASSET_NOT_FOUND_MSG);
//    }
}
