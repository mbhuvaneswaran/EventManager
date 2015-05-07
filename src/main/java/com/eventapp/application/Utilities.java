package com.eventapp.application;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bson.BSONObject;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Utilities {
public static Event GetEventObjectFromDBObject(DBObject obj){
	Event evt=new Event();	
	evt.setName(obj.get("name").toString());
	String address1=((BSONObject)obj.get("place")).get("address1").toString();
	String address2=((BSONObject)obj.get("place")).get("address2").toString();
	String latitude=((BSONObject)obj.get("place")).get("latitude").toString();
	String longitude=((BSONObject)obj.get("place")).get("longitude").toString();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:MM:ss");
	CopyOnWriteArrayList<Invitees> invitees = new CopyOnWriteArrayList<>();
   BasicDBList inviteesList = (BasicDBList) obj.get("invitees");
   for (int i = 0; i < inviteesList.size(); i++) {
       BasicDBObject inviteeObj = (BasicDBObject) inviteesList.get(i);
       
       Invitees ivt=new Invitees();
       ivt.setEmail(inviteeObj.get("email").toString());
       //ivt.setLinkTo(inviteeObj.get("linkTo").toString());
       invitees.add(ivt);    
   }
	Date date = null;
	try {
		date = dateFormat.parse(obj.get("date").toString());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String organizer=obj.get("organizer").toString();
	Place place=new Place();
	place.setAddress1(address1);
	place.setAddress2(address2);
	place.setLatitude(latitude);
	place.setLongitude(longitude);
	evt.setPlace(place);
	evt.setDate(date);
	evt.setInvitees(invitees);
	evt.setOrganizer(organizer);
	return evt;
	
	
}

}
