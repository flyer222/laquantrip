package com.huaxin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveServlet extends HttpServlet {

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GBK");
		
		System.out.println("sex="+req.getHeader("Host"));
		
		System.out.println("name=" +req.getParameter("name"));
		System.out.println("sex="+req.getParameter("sex"));
		System.out.println("pwd="+req.getParameter("pwd"));
		String[] interst=req.getParameterValues("interst");
		for(int i=0 ;i < interst.length;i++)
			 System.out.println(interst[i]);
		 
	}
	
}
