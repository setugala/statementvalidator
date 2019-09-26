/**
 * 
 */
package com.customer.statementvalidator.errorhandler;

import com.customer.statementvalidator.resources.Message;

/**
 * @author GA1357
 *
 */
public class CustomerValidatorApplicationException extends Exception{


  private Message messages;
 

  /**
   * @return the messages
   */
  public Message getMessages() {
    return messages;
  }

  /**
   * @param messages the messages to set
   */
  public void setMessages(Message messages) {
    this.messages = messages;
  }

  /**
   * 
   */
  private static final long serialVersionUID = 5620484314206730435L;

  public CustomerValidatorApplicationException(String message) {
    super(message);
  }
  
  public CustomerValidatorApplicationException(Message messages) {
    super();
    this.messages=messages;
  }
  
}
