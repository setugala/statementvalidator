/**
 * 
 */
package com.customer.statementvalidator.errorhandler;

import org.apache.commons.logging.Log;

import com.customer.statementvalidator.resources.Message;

/**
 * @author GA1357
 *
 */
public class ExceptionFactory {

  /**
   * Method used to log and throw the exception
   * 
   * @param logMethod - log method
   * @param logKey - log key
   * @param exception - exception
   * @param msgKey - message key
   * @param objArr - parameters to be logged
   * @throws PaymentInitiationApplicationException - exception thrown
   */
  public static void logAndThrowApplicationException(final Log logger, String log, Exception exception,
      String errorKey, String errorMessage) throws CustomerValidatorApplicationException {
    logger.error(log, exception);
    throw new CustomerValidatorApplicationException(getMessage(errorKey, errorMessage));
  }

  public static Message getMessage(String errorKey, String errorMessage) {
    Message message = new Message();
    message.setErrorKey(errorKey);
    message.setErrorMessage(errorMessage);
    return message;
  }
}
