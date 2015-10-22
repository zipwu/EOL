<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<style type="text/css">
 		body{font-family:"Microsoft Yahei";}
 	</style>
  <head>
    <base href="<%=basePath%>">
    
    <title>Login</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <div>
   		<form action="LoginServlet" method="post">
   			<label>用户名：</label>
   			<input name="username" type="text" value=""/>
   			<label>密码：</label>
   			<input name="password" type="password"  value=""/>
   			<input type="submit" value="登录" />
   		</form>
   </div>
  </body>
</html>
