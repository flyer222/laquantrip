package com.huaxin.travel.user;

import java.util.List;

import com.huaxin.travel.util.DbUtil;

public class UserService {
	
	public List<User> findUsers(int myid)
	{
		DbUtil db=new DbUtil();
		List<User> list=db.search("select * from User where id!=? ", User.class, myid );
		return list;
	}
	
	
	public int  makeFriends(int myid,int friendid)
	{
		DbUtil db=new DbUtil();
		return db.executeSql("insert into friends(user_id,friend_id) values(?,?)", myid,friendid);
	}
	
	public int addUser(User u)
	{
		DbUtil db=new DbUtil();
		String sql="insert into user(password,nick_name,qq,sex,icon,address,username) values(?,?,?,?,?,?,?) ";
		return db.executeSql(sql,u.getPassword(),u.getNickName(),u.getQq(),u.getSex(),u.getIcon(),u.getAddress(),u.getUsername());
	}
	
	public List<User> searchUsers()
	{
		DbUtil db=new DbUtil();
		return db.search("select * from user", User.class);
	}
	
	
	public User login(String pwd,String username)
	{
		DbUtil db=new DbUtil();
		List<User> users=db.search("select * from user where password=? and username=? ", User.class, pwd,username);
		if(users!=null && users.size()> 0)
		{
			return users.get(0);
		}else
			return null;
	}
	
	public boolean checkUserExsisted(String username){
		DbUtil db=new DbUtil();
		List list=db.search("select * from user where username=? ", User.class, username);
		if(list!=null && list.size()>0)
		{
			return true;
		}else 
			return false;
		
	}
	
	
	
	
	public static void main(String[] args)
	{
		
		UserService s=new UserService();
		
//		User u=new User();
//		u.setPassword("123");
//		u.setNickName("Alibaba");
//		u.setQq("100044");
//		u.setSex("male");
//		u.setIcon("img/default.jpg");
//		u.setAddress("eclipse.lnc.");
//		u.setUsername("Apache org");
//		s.addUser(u);
//		
//		
//		s.makeFriends(1, 7);
//		List list=s.findUsers(1);
//		System.out.println(list.size());
		
		
		
		List<User> us=s.searchUsers();
		for(User u : us)
		{
			System.out.println(u.getId() + u.getUsername());
		}
		
	}
	
}
