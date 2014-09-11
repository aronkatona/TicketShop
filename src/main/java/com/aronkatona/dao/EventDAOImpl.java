package com.aronkatona.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aronkatona.model.Event;

public class EventDAOImpl implements EventDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void saveEvent(Event e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(e);
	}

	@Override
	public void updateEvent(Event e) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEvents() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Event> eventsList = session.createQuery("from Event").list();
		return eventsList;
	}

	@Override
	public Event getEventById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Event e = (Event) session.get(Event.class, new Integer(id));
		return e;
	}

	@Override
	public void removeEvent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Event e = (Event) session.get(Event.class, new Integer(id));
		if(e != null){
			session.delete(e);
		}
	}
}
