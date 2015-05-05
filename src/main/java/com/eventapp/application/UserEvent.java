package com.eventapp.application;

import java.util.List;

import org.bson.types.ObjectId;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UserEvent {
	@JsonSerialize(using=ObjectIdJsonSerializer.class)
	private String eventId;
	private String email;
	private String linkTo;
	private boolean rsvp;
	private int canAccomodate;
	private List<String> guests;
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String objectId) {
		this.eventId = objectId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLinkTo() {
		return linkTo;
	}
	public void setLinkTo(String linkTo) {
		this.linkTo = linkTo;
	}
	public boolean isRsvp() {
		return rsvp;
	}
	public void setRsvp(boolean rsvp) {
		this.rsvp = rsvp;
	}
	public int getCanAccomodate() {
		return canAccomodate;
	}
	public void setCanAccomodate(int canAccomodate) {
		this.canAccomodate = canAccomodate;
	}
	public List<String> getGuests() {
		return guests;
	}
	public void setGuests(List<String> guests) {
		this.guests = guests;
	}
	
	
}
