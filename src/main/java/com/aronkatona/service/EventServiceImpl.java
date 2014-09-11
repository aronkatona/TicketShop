package com.aronkatona.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aronkatona.dao.EventDAO;
import com.aronkatona.dao.UserDAO;
import com.aronkatona.model.Event;

@Service
public class EventServiceImpl implements EventService{
	
	private EventDAO eventDAO;

	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	@Override
	@Transactional
	public void saveEvent(Event e) {
		this.eventDAO.saveEvent(e);
		
	}

	@Override
	@Transactional
	public void updateEvent(Event e) {
		this.eventDAO.updateEvent(e);
		
	}

	@Override
	@Transactional
	public List<Event> getEvents() {
		return this.eventDAO.getEvents();
	}

	@Override
	@Transactional
	public Event getEventById(int id) {
		return this.eventDAO.getEventById(id);
	}

	@Override
	@Transactional
	public void removeEvent(int id) {
		this.eventDAO.removeEvent(id);	
	}

}
