package com.example.greetings;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import com.example.greetings.api.model.Errors;
import com.example.greetings.api.model.ErrorsErrorsInner;
import com.example.greetings.api.model.Error;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler  {

  @Autowired
  private  MessageSource messageSource;
  
  

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Errors handleValidationExceptions(final MethodArgumentNotValidException ex) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();

    ex.getBindingResult().getAllErrors().forEach((error) -> {
      final ErrorsErrorsInner exception = new ErrorsErrorsInner();
      final String fieldName = ((FieldError) error).getField();
      ((FieldError)error).getRejectedValue();
      final String errorMessage = error.getDefaultMessage();
      exception.setField(fieldName);
      exception.setMessage(errorMessage);
      errors.add(exception);
    });
    exceptionResponse.setErrors(errors);
    return exceptionResponse;
  }

  @ResponseBody
  @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Errors handleArgMismatch(final MethodArgumentTypeMismatchException e) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();
    final ErrorsErrorsInner exception = new ErrorsErrorsInner();
    final String fieldName = e.getName();
    final String errorMessage = "should be of type " + e.getRequiredType().getSimpleName();
    exception.setField(fieldName);
    exception.setMessage(errorMessage);
    errors.add(exception);
    exceptionResponse.setErrors(errors);
    log.info(errors.toString());
    return exceptionResponse;
  }



  @ResponseBody
  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public Errors handleArgMismatch(final HttpMessageNotReadableException e) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();
    final ErrorsErrorsInner exception = new ErrorsErrorsInner();
    final String errorMessage = e.getMessage();
    // final String errorMessage = "should be of type " + e.getRequiredType().getSimpleName();
    // exception.setField(fieldName);
    exception.setMessage(errorMessage);
    errors.add(exception);
    exceptionResponse.setErrors(errors);
    return exceptionResponse;
  }

  @ResponseBody
  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
  public Errors handleNonSupportedRequestMethod(
      final HttpRequestMethodNotSupportedException e) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();
    final ErrorsErrorsInner exception = new ErrorsErrorsInner();
    exception.setMessage(messageSource.getMessage("messages.error.methodNotAllowedMessage",
        null, Locale.getDefault()));
    errors.add(exception);
    exceptionResponse.setErrors(errors);
    return exceptionResponse;
   
  }

  @ResponseBody
  @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public Errors handleUnSupportedMediaType(HttpMediaTypeNotSupportedException e) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();
    final ErrorsErrorsInner exception = new ErrorsErrorsInner();
    exception.setMessage(messageSource.getMessage("messages.error.unsupportedMediaTypeMessage",
        null, Locale.getDefault()));
    errors.add(exception);
    exceptionResponse.setErrors(errors);
    return exceptionResponse;
  }

  @ResponseBody
  @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  public Errors handleUnAcceptedMediaType(HttpMediaTypeNotAcceptableException e) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();
    final ErrorsErrorsInner exception = new ErrorsErrorsInner();
    exception.setMessage(messageSource.getMessage("messages.error.unsupportedMediaTypeMessage", null, Locale.getDefault()));
    errors.add(exception);
    exceptionResponse.setErrors(errors);
    return exceptionResponse;
    
  }

  @ExceptionHandler({Throwable.class})
  public Errors handleException(Throwable t) {
    final Errors exceptionResponse = new Errors();
    final List<ErrorsErrorsInner> errors = new ArrayList<ErrorsErrorsInner>();
    final ErrorsErrorsInner exception = new ErrorsErrorsInner();
    exception.setMessage(messageSource
        .getMessage("messages.error.processingErrorMessage", null, Locale.getDefault()));
    errors.add(exception);
    exceptionResponse.setErrors(errors);
    return exceptionResponse;
  }

}
