<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<c:if test="${result == 1 }">
	<script type="text/javascript">
		alert("로그인 성공했습니다!");
		location.href="main.do";
	</script>
</c:if>

<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("비밀번호가 맞지 않습니다!");
		history.go(-1);
	</script>
</c:if>

<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("아이디가 맞지 않습니다!");
		history.go(-1);
	</script>
</c:if>

<c:if test="${result == 3 }">
	<script type="text/javascript">
		alert("탈퇴한 회원입니다")
		history.go(-1);
	</script>
</c:if>

</body>
</html>