package com.eventapp.application;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDetails extends Event  {
	
	private Transport transport=new Transport();
	private boolean rsvp=false;
	private boolean isOrganizer=false;
	public Transport getTransport() {
		return transport;
	}

	public boolean isOrganizer() {
		return isOrganizer;
	}

	public void setIsOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}

	public void setTransport(Transport transport) {

		this.transport = transport;
	}


	public boolean isRsvp() {
		return rsvp;
	}

	public void setRsvp(boolean rsvp) {
		this.rsvp = rsvp;
	}
}
