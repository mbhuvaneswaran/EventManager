package com.eventapp.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Transport {
	private String mode;
	private int canAccomodate=0;
	private Set<String> guests= Collections.emptySet();
	private String linkTo;
	public Set<String> getGuests() {
		return guests;
	}
	public void setGuests(Set<String> guests) {
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
