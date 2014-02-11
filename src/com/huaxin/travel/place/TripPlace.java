package com.huaxin.travel.place;

import java.util.Date;

public class TripPlace {
 
	/*`id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `desc` varchar(45) COLLATE utf8_bin NOT NULL,
  `img_path` varchar(45) COLLATE utf8_bin NOT NULL,
  `hot` int(10) NOT NULL, */
	
	private int id;
	private int user_id;
	private String name;
	private String description;
	private String imgPath;
	private int hot;
	
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	private int state;
	private int min_num;
	private String imgPath2;
	private String imgPath3;
	private Date createtime;
	
	
	public String getImgPath2() {
		return imgPath2;
	}
	public void setImgPath2(String imgPath2) {
		this.imgPath2 = imgPath2;
	}
	public String getImgPath3() {
		return imgPath3;
	}
	public void setImgPath3(String imgPath3) {
		this.imgPath3 = imgPath3;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getMin_num() {
		return min_num;
	}
	public void setMin_num(int min_num) {
		this.min_num = min_num;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int userId) {
		user_id = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	
}
