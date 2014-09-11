package com.aronkatona.service;

import java.util.List;

import com.aronkatona.model.Event;

public interface EventService {

	public void saveEvent(Event e);
	public void updateEvent(Event e);
	public List<Event> getEvents();
	public Event getEventById(int id);
	public void removeEvent(int id);
	
}
