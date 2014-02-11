package com.huaxin.travel.note;

public class Note {
	
	/*`id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `content` varchar(500) COLLATE utf8_bin NOT NULL,
  `title` varchar(45) COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  */
	
	
	private int id;
	private int user_id;
	private String content;
	private String title;
	private String create_time;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String createTime) {
		create_time = createTime;
	}
	
}
