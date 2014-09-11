package com.aronkatona.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aronkatona.dao.ChairDAO;
import com.aronkatona.model.Chair;

@Service
public class ChairServiceImpl implements ChairService{
	
	private ChairDAO chairDAO;

	public void setChairDAO(ChairDAO chairDAO) {
		this.chairDAO = chairDAO;
	}

	@Override
	@Transactional
	public void saveChair(Chair c) {
		this.chairDAO.saveChair(c);
		
	}

	@Override
	@Transactional
	public void updateChair(Chair c) {
		this.chairDAO.updateChair(c);
		
	}

	@Override
	@Transactional
	public List<Chair> getChairs() {
		return this.chairDAO.getChairs();
	}

	@Override
	@Transactional
	public Chair getChairById(int id) {
		return this.chairDAO.getChairById(id);
	}

	@Override
	@Transactional
	public void removeChair(int id) {
		this.chairDAO.removeChair(id);	
	}

}
