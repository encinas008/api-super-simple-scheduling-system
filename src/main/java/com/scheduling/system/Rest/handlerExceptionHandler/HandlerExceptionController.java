package com.scheduling.system.Rest.handlerExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

/**
 *
 * @ControllerAdvice apply globally to all Controllers.
 *
 * Created Rafael Encinas.
 */
@ControllerAdvice
public class HandlerExceptionController extends ResponseEntityExceptionHandler{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Creates a response about of error for the client side.
     *
     * @param exception An exception that is catched in the process of a request.
     * @return A response customized with code and message of error.
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseException> handleControllerException(Exception exception) {
        ResponseException response = new ResponseException(1000, "An error occurred on the operation");
        if(exception instanceof SQLException){
            SQLException sqlException = (SQLException)exception;
            response = new ResponseException(2000, "An error occurred on database");
            log.error(sqlException.toString());
        } else {
            log.error(exception.toString());
        }
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
