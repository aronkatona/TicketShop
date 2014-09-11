package com.aronkatona.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="CHAIR")
public class Chair {
	
	@Id
	@Column(name="CHAIR_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	private int price;
	
	
	private int rNumber;
	private int cNumber;
	
	private boolean reserved;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "EVENT_CHAIR_TABLE", joinColumns = { @JoinColumn(name = "CHAIR_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "EVENT_ID") })
	private List<Event> eventChairs;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "chairs")
	private List<User> userChairs;
	
	public Chair(){
		
	}
	
	public Chair(int price, int rNumber, int cNumber){
		this.price = price;
		this.reserved = false;
		this.rNumber = rNumber;
		this.cNumber = cNumber;
		this.eventChairs = new ArrayList<>();
		this.userChairs = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


	public List<Event> getEventChairs() {
		return eventChairs;
	}

	public void setEventChairs(List<Event> eventChairs) {
		this.eventChairs = eventChairs;
	}

	public int getrNumber() {
		return rNumber;
	}

	public void setrNumber(int rNumber) {
		this.rNumber = rNumber;
	}

	public int getcNumber() {
		return cNumber;
	}

	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}

	public List<User> getUserChairs() {
		return userChairs;
	}

	public void setUserChairs(List<User> userChairs) {
		this.userChairs = userChairs;
	}
	
	
	
	

}
