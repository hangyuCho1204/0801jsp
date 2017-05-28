<%@page import="mypkg.model.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../com/top1.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int myoffset = 2;
	int mywidth = 12 - 2 * myoffset;
	int formleft = 3;
	int formright = 12 - formleft;
	int mysearch = 2;
	//int label = 3 ; //양식의 왼쪽에 보여지는 라벨의 너비 
	//int content = twelve - label ; //우측의 내용 입력(input, select, textarea)의 너비
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
<style type="text/css">
.xxx {
	margin-left: 0px;
}
.re{font-size: 11px;}
</style>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-default panel-primary">
			<div class="panel-heading">
				<form class="form-inline" role="form">
					<h2>게시물 목록</h2>
				</form>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>글 번호</th>
						<th>제목</th>
						<th>작성자</th>	
						<th>작성 일자</th>					
						<th>글 내용</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tr>
				</tr>
				<c:forEach var="bean" items="${requestScope.lists}">
					<tr>
						<td>${bean.seq}</td>
						<td>
							<a href="<%=myComPath%>boDetailView&no=${bean.seq}&${requestScope.parameters}">
								${bean.subject}
							</a>
						</td>
						<td>${bean.writer}</td>
						<td>${bean.wDate}</td>
						<td>${bean.contents}</td>
						<td>
								<a href="<%=myComPath%>boUpdateForm&seq=${bean.seq}&${requestScope.parameters}">
									수정
								</a>
						</td>
						<td>
								<a href="<%=myComPath%>boDelete&seq=${bean.seq}&${requestScope.parameters}">
									삭제
								</a>
							
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div align="center">
			<footer>${requestScope.pagingHtml}</footer>			
		</div>
		
	</div>
	<br><br><br><br>

</body>
</html>