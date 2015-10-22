<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="org.jfree.chart.title.TextTitle"%>
<%@page import="java.awt.Font"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ExamResult.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="ExamResult.css">
	<%
	String name = (String)request.getSession().getAttribute("name");
	String score = (String)request.getSession().getAttribute("score");
	String graphURLPie = (String)request.getSession().getAttribute("graphURLPie");
	%>
  </head>
  
  <body>
    <div class="container">
    <div class="nav">
    <div class="nav_back"></div>
    <div class="nav_text">
    <h4>你好！<%=name %></h4>
    </div>
    </div>
    <div class="examResult">
    <div class="context">
   
    <div class="background"></div>
    <div class="text">
    <h4>你的成绩是<%=score %>分</h4>
    <br>
    <img src="<%= graphURLPie %>" width=700 height=500  border=0>
    </div>
    </div>
    <div class="bottom">
	 中国电信
	 </div>
	 </div>
    </div>
  </body>
</html>
