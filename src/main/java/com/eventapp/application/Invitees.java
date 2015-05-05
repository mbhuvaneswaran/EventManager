package com.eventapp.application;

public class Invitees {
	private boolean rsvp;

    private String email;
    private boolean isOrganizer;
	public boolean isOrganizer() {
		return isOrganizer;
	}

	public void setOrganizer(boolean isOrganizer) {
		this.isOrganizer = isOrganizer;
	}

	private Transport transport;

	public boolean isRsvp() {
		return rsvp;
	}

	public void setRsvp(boolean rsvp) {
		this.rsvp = rsvp;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
