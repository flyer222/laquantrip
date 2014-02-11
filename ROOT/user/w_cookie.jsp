
<% 
Cookie cookie=new Cookie("username","huaxing");
cookie.setMaxAge(24*60*60);
cookie.setPath("/");
response.addCookie(cookie);




cookie=new Cookie("password","123");
cookie.setPath("/");
cookie.setMaxAge(-1);

response.addCookie(cookie);

cookie=new Cookie("userid","1");
cookie.setPath(request.getContextPath()+"/msg/");
cookie.setMaxAge(24*60*60);
response.addCookie(cookie);

session.setAttribute("username","huaxing");

%>


<%=session.getId()%>


//url rewrite
<a href="<%=  response.encodeURL((request.getContextPath()+"/note/r_cookie.jsp")) %>"> link </a>

  write cookie;






