<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 추가</title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
		var ok = confirm("상품이 추가되었습니다. 장바구니로 갈까요?");
		if(ok){
			location.href="cartForm.do?pageNum=${pageNum}";
		}else
			history.go(-1);			
		</script>
	</c:if>
	
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("장바구니에 추가에 실패하였습니다");
			history.go(-1);
		</script>
	</c:if>
	
	<c:if test="${result == -1 }">
		<script type="text/javascript">
		var ok = confirm("이미 장바구니에 상품이 있습니다, 장바구니로 갈까요?");
		if(ok){
			location.href="cartForm.do?pageNum=${pageNum}";
		}else
			history.go(-1);
	</script>
	</c:if>
</body>
</html>