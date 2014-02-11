<%@  page import="java.util.*" %>

<%= session.getId()%><br>
<%= new Date(session.getCreationTime()) %><br>
<%= new Date(session.getLastAccessedTime()) %><br>

<%= session.getAttribute("username") %><br>

<table>

<tr>
	<td> </td>
	<td></td>
	<td>cookie name</td>
	<td>cookie value</td>
</tr>

<% 
	Cookie[] cookies=request.getCookies();
	if(cookies!=null){
	for(int i=0;i<cookies.length;i++){	
		Cookie c=cookies[i];
%>
<tr>
	<td></td>
	<td></td>
	<td><%= c.getName()%></td>
	<td><%= c.getValue()%></td>
</tr>
<%
}
	}
%>
</table>

<a href=" <%= response.encodeURL(request.getContextPath()+"/note/note.jsp")  %>"> link  </a> 

