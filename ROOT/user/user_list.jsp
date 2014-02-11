<%@ page import="com.huaxin.travel.user.*"  pageEncoding="utf-8" %>
<%@ page import="java.util.List;" %>


<script>


function ff(){
	
	var str=" ${sessionScope.User.id }"
	
}

</script>


<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
  <title>朋友列表</title>
 </head>
 <body>

 <br>
 
 
 <%  pageContext.setAttribute("pp","abcdeef");	 %>
 
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


list.usernam

uesrnae =====  ${users[2].username } <br>
uesrnae =====  ${pageScope.pp } <br>
uesrnae =====  ${sessionScope.User.username } <br>
params value ${param.operation }
header   ${header.host} <br>

cookie    ${cookie.JSESSIONID.value}

 map test   ${requestScope.list[0].usernmae }


<br>

<%= request.getAttribute("req_lifecyle")  %>

 </body>
</html>


