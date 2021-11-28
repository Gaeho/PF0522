<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
	<c:if test="${result>0 }">
		<script type="text/javascript">
			alert("게시글이 삭제되었습니다");
			location.href="boardList.do?pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result==0 }">
		<script type="text/javascript">
			alert("없는 게시글입니다");
			location.href="list.do?pageNum=${pageNum}";
		</script>
	</c:if>
	<c:if test="${result<0 }">
			<h3>권한이 없습니다</h3>
			<button type="button" onclick="javascript:history.back();">이전페이지로</button>
	</c:if>
	<!-- =================================================================== -->
</body>
</html>