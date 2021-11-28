<%@ page import="dao.UsersDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("회원가입이 완료 되었습니다");
		location.href="loginForm.do";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("정확한 정보를 입력해주세요");
		location.href="joinForm.do";
	</script>
</c:if>
<c:if test="${result==-1}">
	<script type="text/javascript">
		alert("중복된 아이디");
		location.href="joinForm.do";
	</script>
</c:if>
</body>
</html> 