package com.aronkatona.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="EVENT")
public class Event {
	
	@Id
	@Column(name="EVENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String category;
	
	@Column(nullable = false)
	private String name;
	
	private String location;
	
	private Date date;
	
	private int numberOfSeats;
	
	private int freeSeats;
	
	private int xSize;
	
	private int ySize;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "events")
	private List<User> users;	
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "eventChairs")
	private List<Chair> chairs;
	
	public Event(){
		
	}

	public Event(String category, String name, String location, Date date, int numberOfSeats, int xSize, int ySize) {
		this.category = category;
		this.name = name;
		this.location = location;
		this.date = date;
		this.numberOfSeats = numberOfSeats;
		this.freeSeats = numberOfSeats;
		this.users = new ArrayList<>();
		this.chairs = new ArrayList<>();
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public int getFreeSeats() {
		return freeSeats;
	}

	public void setFreeSeats(int freeSeats) {
		this.freeSeats = freeSeats;
	}

	public void decreaseFreeSeats(int numberOfTicket){
		this.freeSeats -= numberOfTicket;
	}
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Chair> getChairs() {
		return chairs;
	}

	public void setChairs(List<Chair> chairs) {
		this.chairs = chairs;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}
	
	
	
}
