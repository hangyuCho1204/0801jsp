<%@page import="mypkg.model.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
    <%
    	String rootPath = request.getContextPath();
    	String myComPath = rootPath+"/ex0801?command=";
    %>
    <%
    	 /* HttpSession session = request.getSession(); */
    	Member loginfo = (Member)session.getAttribute("loginfo");
       	
    	int whologin = 0;
    	if(loginfo==null){
    		whologin = 0;//�̷α���
    	}else{
    		if(loginfo.getId().equals("admin")){
    			whologin = 2;//������
    		}else{
    			whologin = 1;//�Ϲ�ȸ��
    		}
    	}
    	session.setAttribute("whologin", whologin);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=rootPath%>/main.jsp">Ȩ</a>
	<a href="<%=myComPath%>boList">�Խ��� ����</a>
	<a href="<%=myComPath%>boInsertForm">�Խñ� ���</a>
	
	
	<c:if test="${whologin == 0}">
	<form action="<%=myComPath%>login" method="post">
	<label>���̵�</label>
	<input type="text" id="id" name="id">
	
	<label>���</label>
	<input type="text" id="password" name="password">
	<input type="submit" value="�α���">
	</form>
	</c:if>
	<c:if test="${whologin != 0}">
	<a href="<%=myComPath%>logout">�α׾ƿ�</a>
	</c:if>
	
</body>
</html>