package com.eventapp.application;

public class Transport {
	private String mode;
	private int canAccomodate;
	private String[] guests;
	private String linkTo;
	public String[] getGuests() {
		return guests;
	}
	public void setGuests(String[] guests) {
		this.guests = guests;
	}
	public String getLinkTo() {
		return linkTo;
	}
	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getCanAccomodate() {
		return canAccomodate;
	}
	public void setCanAccomodate(int canAccomodate) {
		this.canAccomodate = canAccomodate;
	}
	
}	
