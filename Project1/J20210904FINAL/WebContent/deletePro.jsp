<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제완료");
		location.href="main.do";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("암호가 틀렸습니다.");
		location.href="deleteForm.do";
	</script>	
</c:if>
<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("권한이 없습니다.");
		location.href="main.do";
	</script>	
</c:if>
</body>
</html>