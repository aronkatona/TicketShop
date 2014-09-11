package com.aronkatona.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.aronkatona.model.Chair;

public class ChairDAOImpl implements ChairDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void saveChair(Chair c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
	}

	@Override
	public void updateChair(Chair c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chair> getChairs() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Chair> chairsList = session.createQuery("from Chair").list();
		return chairsList;
	}

	@Override
	public Chair getChairById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Chair c = (Chair) session.get(Chair.class, new Integer(id));
		return c;
	}

	@Override
	public void removeChair(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Chair c = (Chair) session.get(Chair.class, new Integer(id));
		if(c != null){
			session.delete(c);
		}
	}
}
