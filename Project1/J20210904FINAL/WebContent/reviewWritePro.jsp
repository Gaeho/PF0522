<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
</head>
<body>
<c:if test="${result  > 0 }">
	<script type="text/javascript">
		alert("입력 완료");  
		location.href="reviewList.do?pageNum=${pageNum}&pd_code=${pd_code}";
	</script>
</c:if>
<c:if test="${result == 0 }">  
	<script type="text/javascript">
		alert("입력오류");  
		location.href="reviewWriteForm.do?rev_code=${rev_code}&pageNum=${pageNum}&pd_code=${pd_code}";
	</script>
</c:if>
</body>
</html>