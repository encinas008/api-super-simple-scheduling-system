package com.scheduling.system.Rest.handlerExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * Created Rafael Encinas.
 */
public class HandlerExceptionController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
