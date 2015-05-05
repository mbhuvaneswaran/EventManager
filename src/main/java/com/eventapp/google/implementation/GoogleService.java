package com.eventapp.google.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.eventapp.application.Invitees;
import com.eventapp.exception.ServiceExceptionDetails;
import com.eventapp.google.interfaces.IGoogleService;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.Builder;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.Organizer;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.Oauth2.Userinfo;
import com.google.api.services.oauth2.model.Userinfoplus;

public class GoogleService implements IGoogleService {
	private Session session;
	@Override
	public boolean CreateEvent(String accessToken, String heading,String description,List<Invitees> attendees,
			String displayName, String organizer,
			Date startDate,String lat,String lng) {
		HttpTransport httpTransport = new NetHttpTransport();

		  /** Global instance of the JSON factory. */
		   JsonFactory jsonFactory = new JacksonFactory();
		GoogleCredential credentials = new GoogleCredential().setAccessToken(accessToken);
		Builder b=new Calendar.Builder(httpTransport, jsonFactory, credentials);
		//b.setServicePath(Calendar.DEFAULT_SERVICE_PATH+"?sendNotifications=true");
		//b.setRootUrl(b.get()+"?sendNotifications=true");
		Calendar service = b.setApplicationName("EventApp").build();
		
		
		// Create and initialize a new event
	Event event = new Event();
	event.setDescription(description);
	event.setSummary(heading);
	event.setLocation(lat+','+lng);
	ArrayList<EventAttendee> invitees = new ArrayList<EventAttendee>();
	Iterator<Invitees> attItr=attendees.iterator();
	while(attItr.hasNext()){
		Invitees ivt=attItr.next();
		invitees.add(new EventAttendee().setEmail(ivt.getEmail()));
	}
	
	// ...
	event.setAttendees(invitees);
	Organizer org=new Organizer();
	org.setDisplayName(displayName);
	org.setEmail(organizer);
	event.setOrganizer(org);
	DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
	event.setStart(new EventDateTime().setDateTime(start));
	Date endDate = new Date(startDate.getTime() + 3600000);
	
	DateTime end = new DateTime(endDate, TimeZone.getTimeZone("UTC"));
	event.setEnd(new EventDateTime().setDateTime(end));
	// Insert the new event
	try {
		Event createdEvent = service.events().insert("primary", event).execute();
		return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return false;
	}

	@Override
	public Userinfoplus GetUserInfo(String accessToken) {
		GoogleCredential credentials = new GoogleCredential().setAccessToken(accessToken);
		HttpTransport httpTransport = new NetHttpTransport();

		  /** Global instance of the JSON factory. */
		   JsonFactory jsonFactory = new JacksonFactory();
		Oauth2 userInfoService = new Oauth2.Builder(
				httpTransport, jsonFactory, credentials)
		        .setApplicationName("Test")
		        .build();
		    Userinfoplus userInfo = null;
		    try {
		      userInfo = userInfoService.userinfo().get().execute();
		      if (userInfo != null && userInfo.getId() != null) {
			      return userInfo;
			    }
		      
		    } catch (IOException e) {
		    	
		      e.printStackTrace();
		    }
			return userInfo;
		    
		    
	}
	public static void throwException(ServiceExceptionDetails serviceException) throws ServiceExceptionDetails {
	    ResponseBuilder builder = Response.status(Response.Status.NOT_ACCEPTABLE);
	    builder.type("application/json");
	    //builder.entity(serviceException.getFaultMessage());
	    builder.entity(serviceException);
	    throw new WebApplicationException(builder.build());
	  }
	@Override
	public boolean SendMail(com.eventapp.application.Event event,Userinfoplus info) {
		final String username = "letsgotonow@gmail.com";
		final String password = "letsgotonow!23";
		String host="smtp.gmail.com";
		//String port=new String("587");
		int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
	  
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
	    //session = Session.getInstance(props);
	    session.setDebug(true);


	    
	    try {
	    	 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			Iterator<Invitees> ivtItr=event.getInvitees().iterator();
			while(ivtItr.hasNext()){
				Invitees ivt=ivtItr.next();
	
				message.addRecipient(Message.RecipientType.CC,new InternetAddress(ivt.getEmail()));
				
			}
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(event.getOrganizer()));
			message.setSubject(event.getName());
			message.setText("Hi All\n\n"+event.getDescription()+"\n\nA Calendar event have been created for the same.\n\nRegards,\n"+info.getName());

			Transport.send(message);

			System.out.println("Done");
			return true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		}

	
	
	
}
