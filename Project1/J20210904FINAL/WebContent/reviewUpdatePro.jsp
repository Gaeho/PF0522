<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
</head>
<body>
	<c:if test="${result >0 }">
		<script type="text/javascript">
			alert("수정완료!");
			location.href="reviewList.do?pageNum=${pageNum}&pd_code=${pd_code}";
		</script>
	</c:if>
	<c:if test="${result==0 }">
		<script type="text/javascript">
			alert("수정실패!");
			location.href="reviewUpdateForm.do?rev_code=${review.rev_code}&pageNum=${pageNum}&pd_code=${pd_code}";
		</script>
	</c:if>
	<c:if test="${result<0 }">
		<h3>권한이 없습니다</h3>
		<button type="button" onclick="javascript:histoy.back();">이전페이지로</button>
	</c:if>
</body>
</html>