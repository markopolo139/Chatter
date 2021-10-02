package com.chatter.chatter.web.errorHandler;

import com.chatter.chatter.app.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Autowired
    public HttpServletRequest mHttpServletRequest;

    public ResponseEntity<Object> convertErrorToResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError,apiError.httpStatus);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Object> handleInvalidPasswordException(InvalidPasswordException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Create password that have typed requirements")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Type correct user login and password")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Create user with another login or email")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidContentTypeException.class)
    public ResponseEntity<Object> handleInvalidContentTypeException(InvalidContentTypeException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Send file with correct content type")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(InvalidPathException.class)
    public ResponseEntity<Object> handleInvalidPathException(InvalidPathException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Contact with admin")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage("File not found")
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(UserRequestException.class)
    public ResponseEntity<Object> handleUserAlreadyInFriendsException(UserRequestException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Select another user")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(FolderCreationError.class)
    public ResponseEntity<Object> handleFolderCreationError(FolderCreationError exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Try again later")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(GroupAlreadyExistException.class)
    public ResponseEntity<Object> handleGroupAlreadyExistException(GroupAlreadyExistException exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Create group with another name")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @ExceptionHandler(GroupDoesNotExists.class)
    public ResponseEntity<Object> handleGroupDoesNotExists(GroupDoesNotExists exception) {
        ApiError apiError = ApiError.builder()
                .setDefaultAction("Type correct group name and/or admin login")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage(exception.getMessage())
                .build();

        return convertErrorToResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request
    ) {
        List<ApiSubError> apiSubErrorList = ex.getBindingResult().getFieldErrors().stream()
                .map(i -> new ApiSubError(
                        "Check the rejected value",
                        "Validation failed for: " + i.getRejectedValue() + " - " + i.getDefaultMessage())
                ).collect(Collectors.toList());

        ApiError apiError = ApiError.builder()
                .setDefaultAction("Correct sub errors")
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessage("Error occurred during validation")
                .setApiSubErrors(apiSubErrorList)
                .build();

        return convertErrorToResponseEntity(apiError);
    }
}
