<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 삭제</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제 완료 !");
		location.href="reviewList.do?pageNum=${pageNum}&pd_code=${pd_code}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("삭제 실패!");
		location.href="reviewDeleteForm.do?pageNum=${pageNum}&pd_code=${pd_code}";
	</script>
</c:if>
<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("권한이 없습니다");
		location.href="reviewDeleteForm.do?pageNum=${pageNum}&pd_code=${pd_code}";
	</script>
</c:if>
</body>
</html>