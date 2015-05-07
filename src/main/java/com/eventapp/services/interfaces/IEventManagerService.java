package com.eventapp.services.interfaces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.model.wadl.Description;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;

import com.eventapp.application.Event;
import com.eventapp.application.EventDetails;
import com.eventapp.exception.ServiceExceptionDetails;


@Path("/Event")
public interface IEventManagerService {
	@POST
	@Description(value = "Resource", target = DocTarget.RESOURCE)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Path("/AddEvent")
	public Event AddEvent(@Description("Give Event Details")EventDetails eventDetails,@QueryParam(value = "access_token") String access_token) throws ServiceExceptionDetails;
	
	@GET
	@Description(value = "Resource", target = DocTarget.RESOURCE)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Path("/GetEventDetails/{id}")
	public EventDetails GetEventDetails(@Description("Event ID") @PathParam("id") final String id,@QueryParam(value = "access_token") String access_token) throws ServiceExceptionDetails;
	
	@GET
	@Description(value = "Resource", target = DocTarget.RESOURCE)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Path("/GetEvents")
	public List<Event> GetEvents(@QueryParam(value = "access_token") String access_token) throws ServiceExceptionDetails;
	
	
	@POST
	@Description(value = "Resource", target = DocTarget.RESOURCE)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML })
	@Path("/EditEvent")
	public Event EditEvent(@Description("Give Event Details")EventDetails event,@QueryParam(value = "access_token") String access_token) throws ServiceExceptionDetails;
	
}
