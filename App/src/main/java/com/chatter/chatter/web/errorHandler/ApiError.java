package com.chatter.chatter.web.errorHandler;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    public static final String SUGGESTED_ACTION = "Contact with admin";

    public static final String DEFAULT_ERROR = "Error occurred";

    public String suggestedAction = SUGGESTED_ACTION;

    public String errorMessage = DEFAULT_ERROR;

    public HttpStatus httpStatus;

    public List<ApiSubError> subApiErrors;

    public static Builder builder() {
        return new Builder();
    }

    public ApiError(String suggestedAction, String errorMessage, HttpStatus httpStatus, List<ApiSubError> subApiErrors) {
        this.suggestedAction = suggestedAction;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.subApiErrors = subApiErrors;
    }

    private ApiError() {}

    public static class Builder {
        private ApiError mApiError = new ApiError();

        public Builder setDefaultAction(String defaultAction) {
            mApiError.suggestedAction = defaultAction;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            mApiError.errorMessage = errorMessage;
            return this;
        }

        public Builder serApiSubErrors(List<ApiSubError> subErrors) {
            mApiError.subApiErrors = subErrors;
            return this;
        }

        public Builder setHttpStatus(HttpStatus httpStatus) {
            mApiError.httpStatus = httpStatus;
            return this;
        }

        public ApiError build() {
            return mApiError;
        }
    }

}
