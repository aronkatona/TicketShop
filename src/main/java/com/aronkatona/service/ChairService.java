package com.aronkatona.service;

import java.util.List;

import com.aronkatona.model.Chair;

public interface ChairService {

	public void saveChair(Chair c);
	public void updateChair(Chair c);
	public List<Chair> getChairs();
	public Chair getChairById(int id);
	public void removeChair(int id);
	
}
