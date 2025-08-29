package com.example.walletandsettlement.exceptionHandler;

import com.example.walletandsettlement.exceptions.InvalidTransactionException;
import com.example.walletandsettlement.exceptions.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.awt.print.PrinterException;

@ControllerAdvice
public class CustomExceptionAdvice {

  @ExceptionHandler(InvalidTransactionException.class)
  public ResponseEntity<String> handleResourceNotFoundException(InvalidTransactionException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleInsufficientBalanceException(NotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(PrinterException.class)
  public ResponseEntity<String> handlePrinterException(PrinterException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
