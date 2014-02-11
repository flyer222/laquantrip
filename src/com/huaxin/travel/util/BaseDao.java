package com.huaxin.travel.util;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.huaxin.travel.user.User;

public class BaseDao {

	public static void main(String[] args)
	{
		DbUtil db=new DbUtil();
		User u=db.load(1L, User.class);
		//System.out.println(u.getAddress() + " " +u.getIcon());
		
		List<User> list=db.search("select * from "+User.class.getSimpleName(),User.class);
		
		for( User s : list)
		{
			System.out.println(s.getAddress() + " " +s.getIcon());
		}
		System.out.println("***************");
		
		list=db.search("select * from user where id < ?  and sex=? ",User.class,3L,"2");
		
		for( User s : list)
		{
			System.out.println(s.getAddress() + " " +s.getIcon());
		}
		
		System.out.println("***************");
		String sql="select t2.`name` , t2.img_path , t1.min_num, t1.state, max( t1.current_num ) from trip_place_friends t1 , trip_place t2 " +

				" where t1.trip_place_id = t2.id and t1.state = ? " +

				" GROUP BY t2.`name` , t2.img_path , t1.state , t1.min_num ,t1.trip_place_id ";
		
		
		List<Map> result=db.search(sql, 0 );
		System.out.println("*######"+ result.size());
		for(int i = 0 ; i < result.size() ; i++) {
		    Map map = (Map)result.get(i);
		      Iterator iter=map.keySet().iterator();
		      while(iter.hasNext())
		      {
		    	  System.out.println(   map.get(  iter.next() )  );
		      }
		   }
		
		sql="insert into user(username,password,nick_name,address) values( ?, ? , ? ,?) ";
		int i=db.executeSql(sql,"alex","123","test","湖南");
		System.out.println("i="+i);
		
		
	}
	
}
