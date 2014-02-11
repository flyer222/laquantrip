package com.huaxin.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class JSonTest {
	
	public static void main(String[] args)
	{
		boolean[] b=new boolean[]{true,false,false};
		String [] strs=new String[]{"hello"," world!"};
		
		
		//System.out.println(JSONArray.fromObject(b));
		//System.out.println(JSONArray.fromObject(strs));
		
		Student s=new Student();
		s.setAge(12);
		s.setId(1);
		s.setName("huaxin");
		
		Student s2=new Student();
		s2.setAge(122);
		s2.setId(2);
		s2.setName("huaxin2");
		
		
		Student[] ary=new Student[2];
		ary[0]=s;
		ary[1]=s2;
		System.out.println(JSONArray.fromObject(ary));
		
		
		Test t=new Test();
		t.setAbb(new int[]{2,3,4,5});
		t.setBc(new String[]{"hello","world" });
		
		System.out.println(JSONObject.fromObject(t));
		
		Map map=new HashMap();
		map.put("name", "huaxin");
		map.put("age", 12);
		
		//System.out.println(JSONObject.fromObject(s));
		//System.out.println(JSONObject.fromObject(map));
		
		
	}
	
}
