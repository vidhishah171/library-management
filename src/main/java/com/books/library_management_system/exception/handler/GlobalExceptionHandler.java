package com.books.library_management_system.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.books.library_management_system.bo.GenericErrorResponse;
import com.books.library_management_system.exception.BooksException;
import com.books.library_management_system.exception.DepartmentException;

/**
 * Project library-management
 * 
 * @author Vidhi_s
 * @version 1.0
 * @date Jul 16, 2024
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<GenericErrorResponse> handleDefaultException(
      final HttpRequestMethodNotSupportedException ex) {

    return new ResponseEntity<>(
        new GenericErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage()),
        HttpStatus.METHOD_NOT_ALLOWED);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GenericErrorResponse> handleAuthenticationException(
      final MethodArgumentNotValidException ex) {

    StringBuilder message = new StringBuilder();
    ex.getBindingResult()
        .getAllErrors()
        .forEach((error) -> {
          String errorMessage = error.getDefaultMessage();
          message.append(errorMessage + " ");
        });
    return new ResponseEntity<>(
        new GenericErrorResponse(HttpStatus.BAD_REQUEST.value(), message.toString()),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<GenericErrorResponse> handleDefaultException(
      final NoHandlerFoundException ex) {

    return new ResponseEntity<>(
        new GenericErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<GenericErrorResponse> handleCustomException(final Exception ex) {

    return new ResponseEntity<>(new GenericErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BooksException.class)
  public ResponseEntity<GenericErrorResponse> handleBookException(final BooksException ex) {

    return new ResponseEntity<>(
        new GenericErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(DepartmentException.class)
  public ResponseEntity<GenericErrorResponse> handleDepartmentException(
      final DepartmentException ex) {

    return new ResponseEntity<>(
        new GenericErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
