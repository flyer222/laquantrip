package com.huaxin.travel.place;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huaxin.travel.note.Note;
import com.huaxin.travel.user.User;
import com.huaxin.travel.util.DbUtil;

public class TripPlaceService {//Data Access Object

	/*`id` int(10) NOT NULL AUTO_INCREMENT,
	  `user_id` int(10) DEFAULT NULL,
	  `name` varchar(45) COLLATE utf8_bin NOT NULL,
	  `desc` varchar(45) COLLATE utf8_bin NOT NULL,
	  `img_path` varchar(45) COLLATE utf8_bin NOT NULL,
	  `hot` int(10) NOT NULL, */
	
	
	//发布新的 旅游地
	public int insert(TripPlace tp,int uid)
	{
		DbUtil db=new DbUtil();
		//插入  trip_place
		String sql="insert into tripplace(user_id, name,imgpath,imgpath2,imgpath3,hot,description,min_num,state,create_time ) values( ?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP()) ";
		int n=-1;
		Integer tmpId=null;
		n=db.executeSql(sql,uid,tp.getName(),tp.getImgPath(),tp.getImgPath2(),tp.getImgPath3(),tp.getHot(),tp.getDescription(),tp.getMin_num(),0);
		tmpId =db.getLastId(TripPlace.class.getSimpleName());
		 
		//sql 注入
		//插入tripplace_friends表数据
		sql="insert into tripPlaceFriends (create_user_id,user_id,trip_place_id,current_num) " +
				" values(?,?,?,? )";
		
		n=db.executeSql(sql,uid,uid,tmpId,1);
		
		//创建一个消息
		sql=" insert into note ( user_id,content,title,create_time) values(?,?,?,?)";
		n=db.executeSql(sql, uid,"发布新的活动--"+tp.getName(),"发布新的活动" ,new Date());
		tmpId =db.getLastId(Note.class.getSimpleName());
		
		//查询我的好友
		sql="select u.* from friends f  , user u  where f.user_id= ? and f.friend_id=u.id ";
		List<Map> results=db.search(sql, uid);
		HashMap map;
		//给我所有的好友 发送消息
		for(int i=0;i<results.size();i++)
		{
			map=(HashMap) results.get(i);
			sql=" insert into friendNote(note_id,user_id,reciver_id,state) values(?,?,?,?)";
			db.executeSql(sql, tmpId,uid, Integer.parseInt(map.get("id")+""),0);
		}
		return 0;
	}
	

	public void joinTrip(int tripid, int myId){

		DbUtil db=new DbUtil();
	//	String sql="select max(current_num) from tripplacefriends where trip_place_id= ? ";
		//int curruent_num=db.getMaxValue(sql, tripid);
		
		String sql="select create_user_id, max(current_num) as current_num  from tripplacefriends tf  where tf.trip_place_id=? "
					+" group by create_user_id";
		List<Map> results= db.search(sql, tripid);
		if(results!=null&&results.size()>0)
		{
			Map map=results.get(0);
			sql="insert into tripPlaceFriends (create_user_id,user_id,trip_place_id,current_num) " +
			" values(?,?,?,?)";
			db.executeSql(sql,Integer.parseInt(map.get("create_user_id")+""),myId, tripid , Integer.parseInt(map.get("current_num")+"")+1  );
		}
		
		//创建一个消息
		sql=" insert into note ( user_id,content,title,create_time) values(?,?,?,?)";
		int n=db.executeSql(sql, myId, "参加了新的活动--诚邀请大家一起参与","参加新的活动" ,new Date());
		int tmpId =db.getLastId(Note.class.getSimpleName());
		
		//查询我的好友
		sql="select u.* from friends f  , user u  where f.user_id= ? and f.friend_id=u.id  ";
		results=db.search(sql, myId);
		HashMap map;
		//给我所有的好友 发送消息
		for(int i=0;i<results.size();i++)
		{
			map=(HashMap) results.get(i);
			sql=" insert into friendNote(note_id,user_id,reciver_id,state) values(?,?,?,?)";
			db.executeSql(sql, tmpId,myId, Integer.parseInt(map.get("id")+""),0);
		}
	}
	
	
	public List<User> findTripUsers(int trip_id)
	{
		String sql="select u.* from tripplacefriends tf, user u " +
				" where u.id= tf.create_user_id and tf.trip_place_id= ? ";
		DbUtil db=new DbUtil();
		return db.search(sql, User.class,trip_id);
	}
	
	
	
	public List<TripPlaceFriend> findAllTripPlace(){
		
		String sql="select t2.`name` , t2.id , t2.imgpath , t2.min_num, t2.state, max( t1.current_num ) from tripplacefriends t1 , tripplace t2 "+
					" where t1.trip_place_id = t2.id and t2.state = 0 "+
					" GROUP BY t2.`name` , t2.imgpath , t2.state , t2.min_num ,t1.trip_place_id ,t2.id";
		DbUtil db=new DbUtil();
		return db.search(sql, TripPlaceFriend.class);
		
	}
	
	
	public List<TripPlace> findTopTripplace(int size){
		
		String sql="select * from tripplace t "+
					"where t.state=-1 "+
					"order by hot ,create_time desc " +
					"limit 0,? " ;
		DbUtil db=new DbUtil();
		return db.search(sql, TripPlace.class, size);
	}
	
	
	
	public static void main(String[] args)
	{
		TripPlaceService dao=new TripPlaceService();
		TripPlace t=new TripPlace();
		
		t.setName("new publish222");
		t.setHot(1);
		t.setDescription("this is description222");
		t.setUser_id(1);
		t.setMin_num(20);
		t.setImgPath("img/1.jpg22222");
		//dao.insert(t, 1);
		
		dao.joinTrip(1, 2);
		
	}
	
}
