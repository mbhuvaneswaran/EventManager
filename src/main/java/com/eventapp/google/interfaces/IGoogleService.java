package com.eventapp.google.interfaces;
import java.util.Date;
import java.util.List;

import com.eventapp.application.Invitees;
import com.google.api.services.oauth2.model.Userinfoplus;
public interface IGoogleService {
	
	public boolean CreateEvent(String accessToken,String heading,String description,List<Invitees> attendees,String displayName,String organizer,Date startDate,String lat,String lang);
	public Userinfoplus GetUserInfo(String accessToken);
	public boolean SendMail(com.eventapp.application.Event event,Userinfoplus info); 
}
