<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
<body>
<c:if test="${result==0}">
	<script type="text/javascript">
		alert("추가실패~~~~~~");
		location.href="boardWriteForm.do?pageNum=${pageNum}&pdCode=${pdCode}";
	</script>	
</c:if>
<c:if test="${result>0}">
	<script type="text/javascript">
		alert("추가성공~~~~~~");
		location.href="boardList.do?pageNum=${pageNum}";
	</script>	
</c:if>
</body>
</html>