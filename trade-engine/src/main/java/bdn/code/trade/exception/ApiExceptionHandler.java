package bdn.code.trade.exception;

import bdn.code.trade.message.ApiMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException notFoundException) {

        ApiException apiException = new ApiException(
                notFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                new Date()
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = TradeException.class)
    public ResponseEntity<Object> tradeException(TradeException tradeException) {

        ApiException apiException = new ApiException(
                tradeException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date()
        );
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> defaultException() {

        ApiException apiException = new ApiException(
                ApiMessages.INTERNAL_SERVER_ERROR.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                new Date()
        );
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
