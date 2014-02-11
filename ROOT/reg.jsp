<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
  <title>用户注册</title>
 </head>
 <body>
  


<form method="post" action="user?operation=add">
用户名：<input type="text" name="username"> <br>
密码	<input type="password" name="password"> <br>
昵称<input type="text" name="nickName"> <br>
性别  男<input type="radio" name="sex" value="1"> 女 <input type="radio" name="sex" value="0"> <br>
QQ <input type="text" name="qq"> <br>
地址 <input type="text" name="address"><br>

<input type="submit">
</form>



 </body>
</html>
