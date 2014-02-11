<%@ page import="com.huaxin.travel.user.*"  pageEncoding="utf-8" %>
<%@ page import="java.util.List;" %>

 
<table>
<tr>
	<td> Id </td>
	<td> usernanme </td>
	<td> sex </td>
	<td> </td>
</tr>

<% 
 int j=0;	
List<User> users=(List<User>)request.getAttribute("users");

for(int i=0;i<users.size();i++)
	{
			User u=users.get(i);
			pageContext.setAttribute("t",u);		
			
%>
	<tr>
		<td>  ${t.sex } </td>
		<td> ${t.id }</td>
		<td>  ${t.username}  </td>
		<td>  </td>
	</tr>
<%
}
%>
</table>