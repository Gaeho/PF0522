<%@ page import="dao.UsersDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 확인</title>
</head>
<body>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("사용가능한 아이디 입니다");
	</script>
</c:if>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("이미 사용중인 아이디 입니다");
	</script>
</c:if>
<c:if test="${result==-1 }">
	<script type="text/javascript">
		alert("잘못된 접근입니다.");
	</script>
</c:if>
	<script>
		history.go(-1);
	</script>
</body>
</html>