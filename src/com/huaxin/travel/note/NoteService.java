package com.huaxin.travel.note;

import java.util.Date;
import java.util.List;

import com.huaxin.travel.util.DbUtil;


public class NoteService {

	public List<Note> getUnReadMsg(int myid)
	{
		DbUtil util=new DbUtil();
		String sql=" select n.* from friendnote fn , note n " +
			 	   " where fn.reciver_id =? and n.id =fn.note_id and fn.state=0";
		return util.search(sql, Note.class, myid);
	}
	
	
	public List<Note> getAllMsg(int myid)
	{
		DbUtil util=new DbUtil();
		String sql=" select n.* from friendnote fn , note n " +
			 	   " where fn.reciver_id =? and n.id =fn.note_id ";
		return util.search(sql, Note.class, myid);
	}
	
	public List<Note> getAllMyMsg(int myid)
	{
		DbUtil util=new DbUtil();
		String sql=" select * from note  where user_id =? " ;
		return util.search(sql, Note.class, myid);
	}
	
	public int sendMsg(Note n, int myid, String[] recievers){
		
		String sql="insert into note (user_id, title,content,create_time) values(?,?,?,?)";
		DbUtil util=new DbUtil();
		util.executeSql(sql, myid,n.getTitle(),n.getContent(),new Date());
		
		int noteid=util.getLastId(Note.class.getSimpleName());
		sql="insert into friendnote(note_id,user_id,reciver_id,state) values(?,?,?,?)";
		for(int i=0;i<recievers.length;i++)
		{
			util.executeSql(sql,noteid, myid, Integer.parseInt(recievers[i]),0);
		}
		return 0;
	}
	
	
	public static void main(String[] args)
	{
		NoteService ns=new NoteService();
		Note no=new Note();
		no.setTitle("note test");
		no.setContent("note content test");
		ns.sendMsg(no, 1, new String[]{"2","3"});
		System.out.println("*********************************");
		
		List<Note> notes=ns.getAllMyMsg(1);
		for(int i=0;i<notes.size();i++){
			Note n=notes.get(i);
			System.out.println( n.getTitle() +" "+ n.getContent());
		}
		System.out.println("*********************************");
		notes=ns.getAllMsg(3);
		for(int i=0;i<notes.size();i++){
			Note n=notes.get(i);
			System.out.println( n.getTitle() +" "+ n.getContent());
		}
		System.out.println("*********************************");
		notes=ns.getUnReadMsg(2);
		for(int i=0;i<notes.size();i++){
			Note n=notes.get(i);
			System.out.println( n.getTitle() +" "+ n.getContent());
		}
	}
}
