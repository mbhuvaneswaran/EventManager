package com.eventapp.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceExceptionDetails")
public class ServiceExceptionDetails extends Exception implements Serializable  {
 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1877025324554343074L;
private String faultCode;
  private String faultMessage;

  public ServiceExceptionDetails(String message) {
	  this.faultMessage=message;
  }

  public String getFaultCode() {
    return faultCode;
  }

  public void setFaultCode(String faultCode) {
    this.faultCode = faultCode;
  }

  public String getFaultMessage() {
    return faultMessage;
  }

  public void setFaultMessage(String faultMessage) {
    this.faultMessage = faultMessage;
  }

}