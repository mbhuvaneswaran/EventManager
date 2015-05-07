package com.eventapp.mongo.interfaces;

import java.util.List;

import com.eventapp.application.Event;
import com.eventapp.application.EventDetails;

public interface IMongoEventManager {
	
	public EventDetails GetEventDetails(String eventId,String userEmail);
	public List<Event> GetEvents(String email);
	public Event AddEvent(Event event);
	public EventDetails EditEvent(EventDetails event,String userEmail);
	public Event DeleteEvent(Event event);
	
	
}
