<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 삭제</title>
</head>
<body>
	<c:if test="${empty result }">
		<script type="text/javascript">
			alert("잘못된 접근입니다");
			location.href="main.do";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("장바구니 삭제에 실패하였습니다");
			location.href="cartForm.do";
		</script>
	</c:if>
		<c:if test="${result == 1 }">
		<script type="text/javascript">
			location.href="cartForm.do";
		</script>
	</c:if>
</body>
</html>