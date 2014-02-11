package com.huaxin.travel.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.huaxin.travel.util.BaseServlet;
import com.huaxin.travel.util.Msg;

public class UserServlet extends BaseServlet {

	
	@Override
	protected void doAndroid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User u=new User();
		copyValues(u, new String[]{"password","nick_name","address","username","qq","sex"}, req);
		System.out.println(u.getIcon()+ " "+ u.getNickName());
		
		Map resultMap=new HashMap();
		
		UserService us=new UserService(); 
		
		String action=req.getParameter("operation");
		if("login".equalsIgnoreCase(action)){
			System.out.println("do login "+ u.getPassword() + " "+u.getPassword());
			 User user=us.login(u.getPassword(), u.getUsername());
			 resultMap.put("state", 0);
			 System.out.println("do login "+ user );
			 if(user!=null){
				 req.getSession().setAttribute("LOGIN_USER", user);
				// JSONObject.fromObject(user);
				 resultMap.put("data", user);
				 resultMap.put("msg", Msg.SUCCESS);
			 }
			 else{
				 //resultMap.put("data", user);
				 resultMap.put("msg", Msg.FAILED);
			 }
		 }else if("makeFriends".equalsIgnoreCase(action)){
			 String fid=req.getParameter("fid");
			 us.makeFriends( u.getId(), Integer.parseInt(fid) ); 
			 resultMap.put("state", 0);
			 resultMap.put("msg", Msg.SUCCESS);
		 }
		resp.getWriter().print( JSONObject.fromObject(resultMap));
	}

	@Override
	protected void doWeb(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User u=new User();
		copyValues(u, new String[]{"password","nickName","address","username","qq","sex"}, req);
		u.setIcon("img/default.jpg");
		System.out.println(u.getIcon()+ " "+ u.getNickName());
		
		String action=req.getParameter("operation");
		UserService us=new UserService();
		
		User u1=new User();
		u1.setUsername("huaxin");
		u1.setId(1);
		
		
		
		if("add".equalsIgnoreCase(action)){
			int r=us.addUser(u);
			if(r > 0){
				req.getSession().setAttribute("LOGIN_USER", u);
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
				//resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
			
		}else if("login".equalsIgnoreCase(action)){
			 User user=us.login(u.getPassword(), u.getUsername());
			 if(user!=null){
				 req.getSession().setAttribute("LOGIN_USER", user);
				 resp.sendRedirect(req.getContextPath()+"/index.jsp");
			 }else{
				 resp.sendRedirect(req.getContextPath()+"/login.jsp");
			 }
		}else if("list".equalsIgnoreCase(action)){
			List<User> users=us.searchUsers();
			
			List list=new ArrayList();
			Map m=new HashMap();
			m.put("usernmae", "ggggg");
			list.add(m);
			
			req.setAttribute("list", list);
			
			
			req.setAttribute("req_lifecyle", "huainxg");
			req.setAttribute("users",users);
			  
			req.getRequestDispatcher("/user/user_list.jsp").forward(req ,resp);
		}else if("check".equalsIgnoreCase(action)){
			String username=req.getParameter("uname");
			boolean flag=us.checkUserExsisted(username);
			//resp.getWriter().printf("{existed:"+flag+"}");
			List<User> users=us.searchUsers();
			req.setAttribute("users",users);
			req.getRequestDispatcher("/user/check.jsp").forward(req, resp);
		}
		
	}

}
