<%@page import="dao.questionDAO"%>
<%@page import="entity.exam_question"%>
<%@page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ExamOnline</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="main.css" media="all">
	<%
		int[] num = (int[]) request.getSession().getAttribute("randarray");
		String name = (String) request.getSession().getAttribute("name");
		System.out.println(name);
		ArrayList<exam_question> list = new ArrayList<exam_question>();
		questionDAO qDao = new questionDAO();
		 for (int i = 0; i < num.length; i++) {
			list.add(qDao.getQuestionById(num[i]));
			session.setAttribute(String.valueOf(num[i]),qDao.getAnswerById(num[i]));
		} 
	%>
  </head>
    <body>
    <div class="container">
  	<div class="nav">
  	<div class="nav_back"></div>
  	<div class="nav_text">
  	<h5>这是考试页面&nbsp;&nbsp;&nbsp;&nbsp;你好!<%=name %></h5>
  	</div>
	</div>
	<div class="exam">
	<form action="SubmitServlet" method="post">
	<%
			for(int i=0;i<num.length;i++){
				exam_question eQuestion = list.get(i);
				%>
		 <div class="context" >
		 <div class="background"></div>
		 <div class="text">
		 		<span>序号：<%=i+1%></span><br>
		 		<span>题目：<%=eQuestion.getQuestion() %></span><br>
		 		<div class="option">
		 		<input type="checkbox" name="<%=eQuestion.getID() %>" value="A"/><%=eQuestion.getOptionA() %><br>
		 		<input type="checkbox" name="<%=eQuestion.getID() %>" value="B"/><%=eQuestion.getOptionB() %><br>
		 		<input type="checkbox" name="<%=eQuestion.getID() %>" value="C"/><%=eQuestion.getOptionC() %><br>
		 		<input type="checkbox" name="<%=eQuestion.getID() %>" value="D"/><%=eQuestion.getOptionD() %><br>
		 		</div>
		 		<%-- 答案:<%=eQuestion.getAnswer()%> --%>
		 		<br>
		 		<br>
		 </div>
		 </div>
		 <%
			}
	 %>
	 <div class="bottom">
	 <div class="bottom_back"></div>
	 <div class="bottom_text">
	 <input type="submit"  value="提交试卷" />
	 <input type="reset"  value="重置答案" />
	 </div>
	 </div>
	</form>
	</div>
	</div>
  </body>
</html>
