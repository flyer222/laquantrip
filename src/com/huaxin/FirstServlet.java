package com.huaxin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huaxin.test.Student;

import net.sf.json.JSONObject;


public class FirstServlet extends HttpServlet{
	
	
	private String name;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	public void init() throws ServletException {
		super.init();
		   
		name=getServletConfig().getInitParameter("name");
		System.out.println("servlet init...hello " + name);
	}

	public void destroy()
	{
		System.out.println(" first servlet destroy..");
	}
	
	private String toXML()
	{
		String xml="<?xml version='1.0' encoding='utf-8' ?>";
		StringBuffer sb=new StringBuffer();
		sb.append(xml);
		sb.append("<student><name>华信</name>");
		sb.append("<age>12</age>");
		sb.append("<sex>female</sex>");
		sb.append("</student>");
		
		return sb.toString();
	}
	
	private String toJson()
	{
		///{"age":12,"id":1,"name":"huaxin","sex":""} 
		
		Student s=new Student();
		s.setAge(12);
		s.setId(1);
		s.setName("huaxin");
		
		return JSONObject.fromObject(s).toString();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.reset();
		
		//resp.setContentType("application/json;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String format=req.getParameter("format");
		System.out.println("get parma format" +format);
		
		if(format == null)
			format="json";
		
		if("xml".equalsIgnoreCase(format))
		{
			resp.setContentType("text/xml;charset=utf-8");
			resp.setHeader("aaa", "abc");
			resp.getWriter().print(toXML());
		}else if("json".equalsIgnoreCase(format)){
			
			String version=req.getParameter("version");
			resp.setContentType("application/json;charset=utf-8");
			resp.getWriter().print(toJson());
		}
	}
}
