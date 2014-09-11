package com.aronkatona.manager;

import java.util.List;

import com.aronkatona.model.Event;
import com.aronkatona.model.User;
import com.aronkatona.service.ChairService;
import com.aronkatona.service.EventService;
import com.aronkatona.service.UserService;

public class EventManager {

	private EventService eventService;
	private UserService userService;
	private ChairService chairService;
	
	public void setEventService(EventService eventService){
		this.eventService = eventService;
	}
	
	public EventService getEventService(){
		return eventService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ChairService getChairService() {
		return chairService;
	}

	public void setChairService(ChairService chairService) {
		this.chairService = chairService;
	}

	public void saveEvent(Event event){
		this.eventService.saveEvent(event);
	}
	
	public List<Event> getEvents(){
		return this.eventService.getEvents();
	}
	
	public void buyTicketToEvent(String eventNumber, int chairNumber,String userName){
		Integer eventId = Integer.parseInt(eventNumber);
		Event event = this.eventService.getEventById(eventId);
		event.getChairs().get(chairNumber).setReserved(true);
		this.chairService.updateChair(event.getChairs().get(chairNumber));
		
		
		User user = this.userService.getUserByName(userName);
		user.getEvents().add(event);
		user.getChairs().add(event.getChairs().get(chairNumber));
		this.userService.updateUser(user);

		
	}
	
	
	
}
