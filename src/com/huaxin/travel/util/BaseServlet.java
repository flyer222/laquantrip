package com.huaxin.travel.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

public abstract class BaseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	private Class clz;
	protected abstract void doWeb(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	protected abstract void doAndroid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
	
	protected void setPOJO(Class clz){
		this.clz=clz;
	}
	
	private void CopyValues(Object obj,HttpServletRequest req){
	
		if(clz==null)
			return ;
		else
			CopyValues(clz,obj,req);	
	}
	
	protected void copyValues(Object obj,String[] properties, HttpServletRequest  req )
	{
		if(obj==null)
			return ;
		
		for(int i=0;i<properties.length;i++)
		{
			String value=req.getParameter(properties[i]);
			
			try {
				BeanUtils.setProperty(obj, properties[i],value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void copyValues(Object obj,HttpServletRequest req)
	{
		Map params=req.getParameterMap();
	    try {
			BeanUtils.populate(obj , params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	private void CopyValues(Class clz,Object obj,HttpServletRequest req){
		
		Map params=req.getParameterMap();
	    try {
			BeanUtils.populate(obj , params);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String client=req.getParameter("client");
		if("Android".equalsIgnoreCase(client)){
			resp.setContentType("application/json;charset=utf-8");
			doAndroid(req,resp);
		}else{
			resp.setContentType("text/html;charset=utf-8");
			doWeb(req,resp);
		}
	}
}
