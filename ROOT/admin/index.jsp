<%@ page pageEncoding="utf-8"  contentType="text/html;charset=utf-8" %>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
  <title>index</title>
 </head>
 <body>
  
<ul>
	<li>  <a> 用户注册 </a> </li>
	<li>  <a> 用户登陆 </a> </li>
	<li>  <a> 查找好友 </a> </li>

	<li> ---------------</li>
	<li>  <a> 查看所有旅游地 </a> </li>
	<li>  <a href="<%= request.getContextPath() %>/pub.jsp"> 发布旅游地 </a> </li>
	<li>  <a> 加入旅游地 </a> </li>

	<li> ---------------</li>
	<li>  <a> 我的未读消息 </a> </li>
	<li>  <a> 我的所有消息 </a> </li>
	<li>  <a> 发布消息给好友</a> </li>

</ul>


 </body>
</html>
