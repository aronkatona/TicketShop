package com.aronkatona.dao;

import java.util.List;

import com.aronkatona.model.Chair;


public interface ChairDAO {
	
	public void saveChair(Chair c);
	public void updateChair(Chair c);
	public List<Chair> getChairs();
	public Chair getChairById(int id);
	public void removeChair(int id);

}
