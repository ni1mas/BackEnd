package NiUnaMas.Controller.Exceptions;

/**
 * Created by Robert on 06/04/2017.
 */
import java.util.ArrayList;
import java.util.List;

import NiUnaMas.Controller.Exceptions.InvalidCredentialsLoginException;
import NiUnaMas.Controller.Exceptions.UserAlreadyExistException;
import NiUnaMas.Models.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ContactAlreadyExists.class })
    protected ResponseEntity<Object> handleContactAlreadyExists(RuntimeException e, WebRequest request) {
        ContactAlreadyExists ire = (ContactAlreadyExists) e;
        ApiError error = new ApiError("400", ire.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }


    @ExceptionHandler({ UserAlreadyExistException.class })
    protected ResponseEntity<Object> handleUserAlreadyExist(RuntimeException e, WebRequest request) {
        UserAlreadyExistException ire = (UserAlreadyExistException) e;
        ApiError error = new ApiError("400", ire.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ InvalidCredentialsLoginException.class })
    protected ResponseEntity<Object> handleInvalidCredentialsLoginException(RuntimeException e, WebRequest request) {
        InvalidCredentialsLoginException ire = (InvalidCredentialsLoginException) e;
        ApiError error = new ApiError("400", ire.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ InvalidTypeException.class })
    protected ResponseEntity<Object> handleInvalidTypeException(RuntimeException e, WebRequest request) {
        InvalidTypeException ire = (InvalidTypeException) e;
        ApiError error = new ApiError("400", ire.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ UserDoesNotExistException.class })
    protected ResponseEntity<Object> handleUserDoesNotExistException(RuntimeException e, WebRequest request) {
        UserDoesNotExistException ire = (UserDoesNotExistException) e;
        ApiError error = new ApiError("400", ire.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler({ CodeDoesNotExistException.class })
    protected ResponseEntity<Object> handleCodeDoesNotExistException(RuntimeException e, WebRequest request) {
        CodeDoesNotExistException ire = (CodeDoesNotExistException) e;
        ApiError error = new ApiError("400", ire.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
