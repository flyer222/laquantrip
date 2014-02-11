package com.huaxin.travel.place;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.http.HttpMethodName;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.GenerateUrlRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.huaxin.travel.user.User;
import com.huaxin.travel.util.BaseServlet;
import com.huaxin.travel.util.Config;
import com.huaxin.travel.util.Msg;

public class TripPlaceServlet extends BaseServlet {
	
	static Logger logger = Logger.getLogger(TripPlaceServlet.class.getName());
	
	private String object = "/" + "foo.jpg";

	@Override
	protected void doAndroid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String uid=req.getParameter("uid");
		TripPlaceService tps=new TripPlaceService();
		
		TripPlace p=new TripPlace();
		copyValues(p,new String[]{"name","desc","img_path"},req);
		
		String action=req.getParameter("operation");
		Map map=new HashMap();
		
		logger.info("trip place "+action);
		
		if("publish".equalsIgnoreCase(action)){
			map.put("state", 0);
			int n=tps.insert(p, Integer.parseInt(uid));
			
			if(n>0)
				map.put("msg", Msg.SUCCESS);
			else 
				map.put("msg", Msg.FAILED);
			
		}else if("join".equalsIgnoreCase(action))
		{
			map.put("state", 0);
			String tripid=req.getParameter("tid");
			tps.joinTrip(Integer.parseInt(tripid), Integer.parseInt(uid));
			map.put("msg", Msg.SUCCESS);
		}else if("searchall".equalsIgnoreCase(action)){
			map.put("state", 0);
			List<TripPlaceFriend> list=tps.findAllTripPlace();
			map.put("data",list);
			map.put("msg", Msg.SUCCESS);
		}else if("findTripUser".equalsIgnoreCase(action)){
			map.put("state", 0);
			String tid=req.getParameter("tid");
			List<User> list=tps.findTripUsers(Integer.parseInt(tid));
			System.out.println("dddd  "+list.size());
			map.put("data",list);
			map.put("msg", Msg.SUCCESS);
		}
		
		resp.getWriter().print( JSONObject.fromObject(map));
	}

	@Override
	protected void doWeb(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TripPlaceService tps=new TripPlaceService();
		
		TripPlace p=new TripPlace();
		String action=req.getParameter("operation");
		
		logger.info("do web "+action );
		
		if("publish".equalsIgnoreCase(action))
		{
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
				             }else if("name".equals(name)){
				            	 p.setName(value);
				             }else if("desc".equalsIgnoreCase(name)){
				            	 p.setDescription(value);
				             }
				        } else {
				            processUploadedFile(item,p);
				        }
				    }
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
				
				User u=(User)req.getSession().getAttribute("LOGIN_USER");
				if(u==null){
					logger.error("user not login.");
					resp.sendRedirect("/login.jsp");
				}else{
					tps.insert(p, u.getId());
				}
		}
		List<TripPlaceFriend> pls=tps.findAllTripPlace();
		req.setAttribute("tripplaces", pls);
		//req.getRequestDispatcher("/place/place_list.jsp").forward(req, resp);
		return ;
	}
	
	
	private String uploadToBCS(InputStream is,String filename, long length) throws Exception{
    	BCSCredentials credentials = new BCSCredentials(Config.USER, Config.PWD);
		BaiduBCS baiduBCS = new BaiduBCS(credentials, Config.BCSHOST);
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("image/jpg");
		objectMetadata.setContentLength(length);
      	//InputStream input = new ByteArrayInputStream(bs);
		logger.debug("upload file path1 "+filename);
		PutObjectRequest request = new PutObjectRequest(Config.BUCKET, filename, is, objectMetadata);
		ObjectMetadata result = baiduBCS.putObject(request).getResult();
		logger.debug("upload file path2 "+result.getContentLength());
		GenerateUrlRequest generateUrlRequest = new GenerateUrlRequest(HttpMethodName.GET, Config.BUCKET,filename);
		return baiduBCS.generateUrl(generateUrlRequest);
    }
	
	private void processUploadedFile(FileItem item,TripPlace p){
		
		if (!item.isFormField()) {
		    String fieldName = item.getFieldName();
		    String fileName = item.getName();
		    String contentType = item.getContentType();
		    boolean isInMemory = item.isInMemory();
		    long sizeInBytes = item.getSize();
		    logger.info("filename="+fileName + " fieldName="+fieldName+" contentType="+contentType+" isInMemory"+isInMemory);
		    String filename="/"+System.currentTimeMillis()+".png";
		    
		    try {
		    	String fileurl=null;
		    	 if("img".equalsIgnoreCase(fieldName)&&StringUtils.isNotEmpty(fileName)){
		    		fileurl = uploadToBCS(item.getInputStream(),filename,sizeInBytes);
			    	p.setImgPath(fileurl);
		    	 }else if("img2".equalsIgnoreCase(fieldName)&&StringUtils.isNotEmpty(fileName)){
		    		fileurl = uploadToBCS(item.getInputStream(),filename,sizeInBytes);
			    	p.setImgPath2(fileurl);
		    	 }else if("img3".equalsIgnoreCase(fieldName)&&StringUtils.isNotEmpty(fileName)){
		    		fileurl = uploadToBCS(item.getInputStream(),filename,sizeInBytes);
			    	p.setImgPath3(fileurl);
		    	 }
		    	 logger.info("upload to BaiduBCS fileurl="+fileurl );
			} catch (Exception e) {
				logger.error("write file failed "+e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
