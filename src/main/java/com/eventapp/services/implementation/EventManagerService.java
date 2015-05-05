package com.eventapp.services.implementation;

import java.util.List;

import com.eventapp.application.Event;
import com.eventapp.application.EventDetails;
import com.eventapp.exception.ServiceExceptionDetails;
import com.eventapp.google.implementation.GoogleService;
import com.eventapp.mongo.implementation.MongoEventManager;
import com.eventapp.services.interfaces.IEventManagerService;
import com.google.api.services.oauth2.model.Userinfoplus;

public class EventManagerService implements IEventManagerService{
	MongoEventManager evtManager=new MongoEventManager();
	GoogleService googleService=new GoogleService();
	@Override
	public Event AddEvent(EventDetails eventDetails,String accessToken) throws ServiceExceptionDetails {
		// TODO Auto-generated method stub
		//Add Google Calendar event
		
		Userinfoplus usrInfo=googleService.GetUserInfo(accessToken);
		if(usrInfo!=null){
			googleService.CreateEvent(accessToken,eventDetails.getName() ,eventDetails.getDescription(), 
					eventDetails.getInvitees(), eventDetails.getName(), eventDetails.getOrganizer(), eventDetails.getDate(),
					eventDetails.getPlace().getLatitude(),eventDetails.getPlace().getLongitude());
			googleService.SendMail(eventDetails,usrInfo);
			return evtManager.AddEvent(eventDetails);
		}else{
			
			GoogleService.throwException(new ServiceExceptionDetails("Invalid User Credentials"));
		}
		return null;
	}

	@Override
	public Event GetEventDetails(String id,String accessToken) throws ServiceExceptionDetails {
		// TODO Auto-generated method stub
		Userinfoplus usrInfo=googleService.GetUserInfo(accessToken);
		if(usrInfo!=null)
	return	evtManager.GetEventDetails(id);
		else
			 return null;
	
	}

	@Override
	public List<Event> GetEvents(String accessToken) throws ServiceExceptionDetails{
		// TODO Auto-generated method stub
		Userinfoplus usrInfo=googleService.GetUserInfo(accessToken);
		if(usrInfo!=null)
		return	evtManager.GetEvents(usrInfo.getEmail());
		else
			return null;
	}

	@Override
	public Event EditEvent(Event event,String accessToken) throws ServiceExceptionDetails {
		// TODO Auto-generated method stub
		Userinfoplus usrInfo=googleService.GetUserInfo(accessToken);
		if(usrInfo!=null)
		return	evtManager.EditEvent(event);
		else
			return null;
	}

	

}
