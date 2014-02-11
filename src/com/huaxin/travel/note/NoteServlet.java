package com.huaxin.travel.note;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.huaxin.travel.user.User;
import com.huaxin.travel.util.BaseServlet;
import com.huaxin.travel.util.Msg;

public class NoteServlet extends BaseServlet {
	
	

	@Override
	protected void doAndroid(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User u=new User();
		copyValues(u,new String[]{"id" },req);
		
		Map map=new HashMap();
		
		String action=req.getParameter("opeartion");
		NoteService ns=new NoteService();
		if("unread".equalsIgnoreCase(action))
		{
			List<Note> list=ns.getUnReadMsg(u.getId());
			map.put("state", "0");
			map.put("data", list);
			map.put("msg", Msg.SUCCESS);
			
		}else if("mymsg".equalsIgnoreCase(action)){
			map.put("state", "0");
			List<Note> list=ns.getAllMyMsg(u.getId());
			map.put("data", list);
			map.put("msg", Msg.SUCCESS);
			
		}else if("sendmsg".equalsIgnoreCase(action)){
			map.put("state", "0");
			//List<Note> list=ns.getAllMyMsg(u.getId());
			Note note=new Note();
			copyValues(note,new String[]{"title","content"},req);
			String[] recivers=req.getParameterValues("rid");
			ns.sendMsg(note, u.getId(), recivers);
			//map.put("data", list);
			map.put("msg", Msg.SUCCESS);
		}
		resp.getWriter().print(JSONObject.fromObject(map));
	}

	@Override
	protected void doWeb(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

}
