package com.eventapp.application;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.xml.bind.annotation.XmlRootElement;


import org.codehaus.jackson.map.annotate.JsonSerialize;
@XmlRootElement
public class Event {
		private String name;
		
		private String _id;
		
		private String description;
		
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		private CopyOnWriteArrayList<Invitees> invitees=new CopyOnWriteArrayList<Invitees>();
		 public void setInvitees(CopyOnWriteArrayList<Invitees> invitees) {
			this.invitees = invitees;
		}
		private Place place;


		
		private Date date;
		private String organizer;
	
		
		public List<Invitees> getInvitees() {
			return (List<Invitees>) invitees;
		}
		
		public String get_id() {
			return _id;
		}

		public void set_id(String _id) {
			this._id = _id;
		}

		public Place getPlace() {
			return place;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setPlace(Place place) {
			this.place = place;
		}
		//@JsonSerialize(using=JsonDateSerializer.class)
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getOrganizer() {
			return organizer;
		}
		public void setOrganizer(String organizer) {
			this.organizer = organizer;
		}
}
