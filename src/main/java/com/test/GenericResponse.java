package com.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GenericResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8629844856439026351L;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private boolean success;
	private String message;

}
