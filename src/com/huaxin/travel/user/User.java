package com.huaxin.travel.user;

import net.sf.json.JSONObject;

public class User {

	/*`id` int(10) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `nick_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `QQ` varchar(20) COLLATE utf8_bin NOT NULL,
  `sex` varchar(4) COLLATE utf8_bin NOT NULL,
  `icon` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(90) COLLATE utf8_bin NOT NULL,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  */
	
	private int id;
	private String  password;
	private String nickName;
	private String qq;
	private String sex;
	private String icon;
	private String address;
	private String username;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	private String toJson()
	{
		return JSONObject.fromObject(this).toString();
	}
	
	
	
}
