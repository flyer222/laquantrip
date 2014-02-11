package com.huaxin.travel.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		
	    FileItemFactory factory = new DiskFileItemFactory();
	 // 创建一个新的文件上传处理器
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items;
		try {
			items = upload.parseRequest(req);
			Iterator iter = items.iterator();
		    
			while (iter.hasNext()) {
		        FileItem item = (FileItem) iter.next();

		        if (item.isFormField()) {
		        	 String name = item.getFieldName();
		             String value = item.getString();
		             if("client".equals(name)){
		            	 System.out.println( name+ " = " + value);
		             }else if("name".equals(name)){
		            	 System.out.println( name+ " = " + value);
		             }else if("desc".equalsIgnoreCase("name")){
		            	 System.out.println( name+ " = " + value);
		             }
		        } else {
		            processUploadedFile(item);
		        }
		    }
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	private void processUploadedFile(FileItem item){
		
		if (!item.isFormField()) {
		    String fieldName = item.getFieldName();
		    String fileName = item.getName();
		    String contentType = item.getContentType();
		    boolean isInMemory = item.isInMemory();
		    long sizeInBytes = item.getSize();
		    
		    String path=getServletContext().getRealPath("img")+"/"+System.currentTimeMillis()+".png";
		    File file=new File( path);
		    try {
				item.write(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
