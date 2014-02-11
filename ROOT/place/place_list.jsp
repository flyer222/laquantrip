<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>旅游地列表</title>
</head>
<body>
	
	<table>
	
	
<tr>
	<th>旅游地名称</th>
	<th>当前人数</th>
	<th>最低人数</th>
	<th>描述</th>
</tr>

<c:forEach var="item" items="${tripplaces}" varStatus="t">
<tr>
	<td>${t.name}</td>
	<td>${t.currentNum}</td>
	<td>${t.minNum}</td>
	<td>${t.minNum}</td>
</tr>
</c:forEach>


</table>
	
	
	

	
		

	
</body>
</html>