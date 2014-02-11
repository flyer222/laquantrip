package com.huaxin.travel.place;

public class TripPlaceFriend {
	
	/*
	`id` int(10) NOT NULL AUTO_INCREMENT,
	  `create_user_id` int(10) NOT NULL,
	  `trip_place_id` int(10) NOT NULL,
	  `min_num` int(4) NOT NULL,
	  `current_num` int(3) NOT NULL,
	  `state` int(1) NOT NULL, */
	
	
	private int id;
	private int createUserId;
	private int minNum;
	private int currentNum;
	private int state;
	private int hot;
	
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	public int getMinNum() {
		return minNum;
	}
	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}
	public int getCurrentNum() {
		return currentNum;
	}
	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
