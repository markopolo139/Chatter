package com.chatter.chatter.web.errorHandler;

public class ApiSubError {

    public String defaultAction;

    public String errorMessage;

    public static Builder builder() {
        return new Builder();
    }

    public ApiSubError(String defaultAction, String errorMessage) {
        this.defaultAction = defaultAction;
        this.errorMessage = errorMessage;
    }

    private ApiSubError() {}

    public static class Builder {
        private ApiSubError mApiSubError = new ApiSubError();

        public Builder setDefaultAction(String defaultAction) {
            mApiSubError.defaultAction = defaultAction;
            return this;
        }

        public Builder setErrorMessage(String errorMessage) {
            mApiSubError.errorMessage = errorMessage;
            return this;
        }

        public ApiSubError build() {
            return mApiSubError;
        }
    }

}
