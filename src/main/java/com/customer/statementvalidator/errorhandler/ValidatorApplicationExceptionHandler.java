/**
 * 
 */
package com.customer.statementvalidator.errorhandler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author GA1357
 *
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidatorApplicationExceptionHandler extends ResponseEntityExceptionHandler  {

  private static final Log LOGGER = LogFactory.getLog(ValidatorApplicationExceptionHandler.class);

  /**
   * Method handles any unknown exception caught
   * 
   * @param exc - Exception thrown
   * @return -json error response
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleUncaughtExceptions(Exception exception) {
    LOGGER.error("Unhandled exception occured", exception);
    return new ResponseEntity<>(ExceptionFactory.getMessage("ERR_MSG_9999", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);

  }
  
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
      LOGGER.error("Unhandled exception occured", exception);
      return new ResponseEntity<>(ExceptionFactory.getMessage("ERR_MSG_9999", "Internal server error"), status);
    } else if (HttpStatus.BAD_REQUEST.equals(status)) {
      return new ResponseEntity<>(ExceptionFactory.getMessage("ERR_MSG_9998", "Invalid Input"), status);
    } else {
      return new ResponseEntity<>(ExceptionFactory.getMessage("ERR_MSG_9999", "Internal server error"), status);
    }
  }


}
