package com.eventapp.mongo.implementation;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

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
	public Event GetEventDetails(String eventID) {
		// TODO Auto-generated method stub
		try {
			if(client==null)
			client = new MongoClient();
			DB db=client.getDB("EventManager");
			DBCollection collection=db.getCollection("Events");
			BasicDBObject obj=new BasicDBObject("_id",(eventID));
			Cursor cur=collection.find(obj);
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			if(cur.hasNext())
				return gson.fromJson(gson.toJson(cur.next()), Event.class);
				
				
			
			
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
	public Event EditEvent(Event event) {
		// TODO Auto-generated method stub
		try{
			if(client==null)
			client = new MongoClient();
			DB db=client.getDB("EventManager");
			DBCollection eventCollection=db.getCollection("Events");
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
			DBObject dbObject = (DBObject)JSON.parse(gson.toJson(event));
			BasicDBObject searchQuery = new BasicDBObject().append("_id",event.get_id());
			dbObject.removeField("_id");
			dbObject.removeField("organizer");
			dbObject.removeField("name");
			eventCollection.update(searchQuery, dbObject);
			return event;
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

}
