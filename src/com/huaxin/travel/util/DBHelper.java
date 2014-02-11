package com.huaxin.travel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class DBHelper {
	
	static String driver = "com.mysql.jdbc.Driver";
	//String url = "jdbc:mysql://127.0.0.1:3306/travel";
    String url = "jdbc:mysql://sqld.duapp.com:4050/HjaVIggutsBcbULBuEVc";
	String user = "flyer222";
	String password = "Aa123456";
	
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn(){
		try {
			return DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws SQLException 
	{
		DBHelper db=new DBHelper();
		Connection con=db.getConn();
		
		String username="Baidu";
		String password="123";
		
		con.setAutoCommit(false);
		String sql="insert into note(user_id,content,title,create_time) values(?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, 1);
		ps.setString(2, "new Content");
		ps.setString(3, "title");
		ps.setTimestamp(4,new java.sql.Timestamp( new java.util.Date().getTime()));
		ps.addBatch();
		

		ps.setInt( 2, 2);
		ps.setString(2, "new2 Content");
		ps.setString(3, "title2");
		ps.setTimestamp(4,new java.sql.Timestamp( new java.util.Date().getTime()));
		ps.addBatch();
		
		sql="insert into note(user_id,content,title,create_time) values(1,'content_test','title_test',CURRENT_TIMESTAMP())";
		ps.addBatch(sql);
		
		int[] re=ps.executeBatch();
		for(int i=0;i<re.length;i++){
			System.out.println(re[i]);
		}
		
		Savepoint sp1=con.setSavepoint("sp1");
		
		sql="insert into note(user_id,content,title,create_time) values(1,'content_test2','title_test2',CURRENT_TIMESTAMP())";
		Statement st=con.createStatement();
		st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		java.sql.ResultSet rs = st.getGeneratedKeys();
		if(rs.next())
			System.out.println("maxId=" +rs.getInt(1));
		
		
		sql="select * from user";
		rs=st.executeQuery(sql);
		while(rs.next()){
			System.out.println(rs.getInt(1));
		}
		
		sql="insert into note(user_id2,content,title,create_time) values(1,'content_test3','title_test3',CURRENT_TIMESTAMP())";
		st=con.createStatement();
		try{
			int i=st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = st.getGeneratedKeys();
			if(rs.next())
				System.out.println("maxId=" +rs.getInt(1));
		}catch(Exception f)
		{
			con.rollback(sp1);
		}
		
		
		/*
		rs=st.executeQuery("select max(id) from note" );
		if(rs.next())
			System.out.println("maxId=" +rs.getInt(1));
		*/
		//sql="select * from user where username='"+username + "' and  password='"+password+"'";
		//ps.addBatch(sql);
		
		
		sql="insert into User(password,username,nick_name,icon,address) values(?,?,?,?,?)";
		PreparedStatement ps2=con.prepareStatement(sql);
		ps2.setString(1, "321");
		ps2.setString(2, "huawei");
		ps2.setString(3, "nick_name");
		ps2.setString(4, "icon");
		ps2.setString(5, "address");
		ps2.addBatch();
		
		re=ps2.executeBatch();
		for(int i=0;i<re.length;i++){
			System.out.println(re[i]);
		}
		
		sql="select * from user where username='"+username + "' and  password='"+password+"'";
		System.out.println(sql);
		con.commit();
		con.close();
	}
	
	
}
