package com.eventapp.mongo.implementation;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.eventapp.application.EventDetails;
import com.google.api.services.oauth2.model.Userinfoplus;
import org.bson.types.ObjectId;

import com.eventapp.application.Event;
import com.eventapp.application.Invitees;
import com.eventapp.application.Transport;
import com.eventapp.mongo.interfaces.IMongoEventManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class MongoEventManager implements IMongoEventManager {
	static MongoClient client = null;

	@Override
	public EventDetails GetEventDetails(String eventID,String userEmail) {
		// TODO Auto-generated method stub
		try {
			if(client==null)
			client = new MongoClient();
			DB db=client.getDB("EventManager");
			DBCollection collection=db.getCollection("Events");
			BasicDBObject obj=new BasicDBObject("_id",(eventID));
			Cursor cur=collection.find(obj);
			System.out.println(cur);
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			EventDetails eventDetails=null;
			if(cur.hasNext())
				eventDetails=gson.fromJson(gson.toJson(cur.next()), EventDetails.class);
				if(userEmail.equalsIgnoreCase(eventDetails.getOrganizer())){
					eventDetails.setIsOrganizer(true);
				}
			Iterator<Invitees> ivtItr=eventDetails.getInvitees().iterator();
			while(ivtItr.hasNext()){
				Invitees ivt=ivtItr.next();
				if(userEmail.equalsIgnoreCase(ivt.getEmail())){
					eventDetails.setIsOrganizer(ivt.isOrganizer());
					eventDetails.setTransport(ivt.getTransport());
					eventDetails.setRsvp(ivt.isRsvp());
					eventDetails.getInvitees().remove(ivt);
					break;
				}
			}
			return eventDetails;
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Event AddEvent(Event event) {
		// TODO Auto-generated method stub
		
		try {
			if(client==null)
			client = new MongoClient();
			DB db=client.getDB("EventManager");
			DBCollection eventCollection=db.getCollection("Events");
			ObjectId id=new ObjectId();
			event.set_id(id.toString());
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			DBObject dbObject = (DBObject)JSON.parse(gson.toJson(event));
			Invitees org=new Invitees();
			org.setOrganizer(true);
			org.setEmail(event.getOrganizer());
			org.setRsvp(true);
			Transport transport=new Transport();
			DBObject transportObject=(DBObject) dbObject.get("transport");
			transport.setCanAccomodate((int)transportObject.get("canAccomodate"));
			transport.setMode(transportObject.get("mode").toString());
			org.setTransport(transport);
			event.getInvitees().add(org);
			dbObject = (DBObject)JSON.parse(gson.toJson(event));
			dbObject.removeField("transport");
			WriteResult result=eventCollection.insert(dbObject);
			
			//DBCollection collection=db.getCollection("EventsUsers");
			/*
			Iterator<Invitees> itr=event.getInvitees().iterator();
			
			while(itr.hasNext()){
				Invitees invitee=itr.next();
				UserEvent ue=new UserEvent();
				ue.setEventId(id.toString());
				ue.setEmail(invitee.getEmail());
				DBObject dbObj=(DBObject)JSON.parse(gson.toJson(ue));
				collection.insert(dbObj);
			}
			//Add Organizer to list
			UserEvent ue=new UserEvent();
			ue.setEventId(id.toString());
			ue.setEmail(event.getOrganizer());
			DBObject dbObj=(DBObject)JSON.parse(gson.toJson(ue));
			collection.insert(dbObj);
			*/
			return event;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public EventDetails EditEvent(EventDetails event,String userEmail) {
		// TODO Auto-generated method stub
		Invitees orgUserIvt=null;
		try{
			if(client==null)
			client = new MongoClient();
			Event orgEvent=getEvent(event.get_id());

			//if organizer can change anything
			if(event.getOrganizer().equalsIgnoreCase(userEmail)){


			}
			Iterator<Invitees> itr=orgEvent.getInvitees().iterator();
			while(itr.hasNext()){
				Invitees ivt=itr.next();
		//		System.out.println(ivt.getEmail());
				if(userEmail.equalsIgnoreCase(ivt.getEmail())){

					ivt.setRsvp(event.isRsvp());
					Transport trp=ivt.getTransport();
					trp.setCanAccomodate(event.getTransport().getCanAccomodate());
					trp.setMode(event.getTransport().getMode());
					orgUserIvt=ivt;
					break;
				}
			}
			orgEvent.setInvitees((CopyOnWriteArrayList)event.getInvitees());
			orgEvent.getInvitees().add(orgUserIvt);
			if(orgUserIvt!=null) {
				Iterator<Invitees> eventInvtItr = event.getInvitees().iterator();
				while (eventInvtItr.hasNext()) {
					Invitees eventIvt = eventInvtItr.next();
					if(eventIvt.getTransport()!=null) {
						if (userEmail.equals(eventIvt.getTransport().getLinkTo())) {
							Transport trans = orgUserIvt.getTransport();
							if (trans != null)
								trans.getGuests().add(eventIvt.getEmail());
						}
					}
				}
			}
			else{
				System.out.println("Null ORg");
			}
			//Commit the Obj

			DB db=client.getDB("EventManager");
			DBCollection eventCollection=db.getCollection("Events");
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			DBObject dbObject = (DBObject)JSON.parse(gson.toJson(orgEvent));
			System.out.println(dbObject.toString());
			BasicDBObject searchQuery = new BasicDBObject().append("_id", event.get_id());
			eventCollection.update(searchQuery, dbObject);
			return GetEventDetails(orgEvent.get_id(),userEmail);
		}
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Event DeleteEvent(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> GetEvents(String email) {
		try {
			if(client==null)
			client = new MongoClient();
			DB db=client.getDB("EventManager");
			DBCollection collection=db.getCollection("Events");
			BasicDBObject obj=new BasicDBObject("invitees",new BasicDBObject("$elemMatch",new BasicDBObject("email",email)));
			Cursor cur=collection.find(obj,new BasicDBObject("invitees.transport",0).append("invitees.isOrganizer",0));
			List<Event> evts=new ArrayList<Event>();
			 Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			
			 while(cur.hasNext()){
				DBObject dbObj=cur.next();
				Event evt=gson.fromJson(gson.toJson(dbObj), Event.class);
				evt.set_id((dbObj.get("_id")).toString());
				evts.add(evt);
			}
			return evts;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Event getEvent(String id){
		DB db=client.getDB("EventManager");
		DBCollection collection=db.getCollection("Events");
		BasicDBObject obj=new BasicDBObject("_id",(id));
		Cursor cur=collection.find(obj);
		Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
		if(cur.hasNext()) {
			return gson.fromJson(gson.toJson(cur.next()), Event.class);
		}
		return null;
	}
}
