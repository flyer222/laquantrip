<%@ page pageEncoding="utf-8"  contentType="text/html;charset=utf-8" %>
<html>
 <head>
  <title>发布</title>
 </head>
 <body>
 
 <form method="POST" enctype="multipart/form-data" action="<%=request.getContextPath()%>/tripplace?client=web&operation=publish">  
名称<input type="text" name="name"><br/> 
最小人数:<input type="text" name="min_num"><br/>
描述<textarea name="desc" rows="4" cols="50"></textarea><br/> 
图片文件1<input type="file" name="img"><br/>
图片文件2<input type="file" name="img2"><br/>
图片文件3<input type="file" name="img3"><br/>
  <input type="submit" value="发布">
  </form>
 </body>
</html>
