<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../com/top1.jsp"%>
<%
	int myoffset = 2;
	int mywidth = 12 - 2 * myoffset;
	int formleft = 3 ;
	int formright = 12 - formleft ; 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BootStrap Sample</title>
<script type="text/javascript">
	function cancel(){
		location.href='<%=myComPath%>boList';
	}
</script>
</head>
<body>
	<div class="container col-sm-offset-<%=myoffset%> col-sm-<%=mywidth%>">
		<div class="panel panel-default panel-primary">
			<div class="panel-heading"><h4>게시물 등록</h4></div>
			<div class="panel-body">
				<form class="form-horizontal" role="form" action="<%=myComPath%>boInsert"  method="post">
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="subject">글 제목</label>
						<div class="col-sm-<%=formright%>">
							<input type="text" class="form-control" name="subject" id="subject"								
								placeholder="글 제목" value="프레임워크">
						</div>
					</div>	
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="writer">작성자</label>
						<div class="col-sm-<%=formright%>">
							<input type="text" class="form-control" name="writer" id="writer"								
								placeholder="작성자" value="hong">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="wDate">작성 일자</label>
						<div class="col-sm-<%=formright%>">
							<input type="datetime" class="form-control" name="wDate" id="wDate"								
								placeholder="작성 일자" value="20160101">
						</div>
					</div>		
					<div class="form-group">
						<label class="control-label col-sm-<%=formleft%>" for="contents">글 내용</label>
						<div class="col-sm-<%=formright%>">
							<textarea name="contents" id="contents" rows="5" cols="" 
								placeholder="글 내용" class="form-control">너무 잼있어요</textarea>								
						</div>
					</div>					
									
					<div class="form-group">
						<div align="center" class="col-sm-offset-3 col-sm-6">
							<button class="btn btn-default" type="submit">새글 쓰기</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" type="reset" onclick="cancel()">취소</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
    		$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
	
</body>
</html>





