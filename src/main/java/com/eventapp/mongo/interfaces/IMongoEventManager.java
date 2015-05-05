package com.eventapp.mongo.interfaces;

import java.util.List;

import com.eventapp.application.Event;

public interface IMongoEventManager {
	
	public Event GetEventDetails(String eventId);
	public List<Event> GetEvents(String email);
	public Event AddEvent(Event event);
	public Event EditEvent(Event event);
	public Event DeleteEvent(Event event);
	
	
}
