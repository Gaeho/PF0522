<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세</title>
<style type="text/css">
.span1 {
	position:absolute;
    color:black;
	font-size:30px;
	left:50%;
	transform:translate(-670px, -20px);
}

table {
	width: 80%;
	margin-left:auto;
	margin-right:auto;
}

</style>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/review/reviewContent.css" type="text/css">
<!-- Web Font Link-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora&family=Noto+Sans+KR&family=Raleway:wght@300&display=swap" rel="stylesheet">
</head>
<body>
<div id="body-wrapper">
	<div id="body-content">
	<!-- Navigation Section -->
		<div class="intro-background">
			<div class="header">	
				<ul class="nav">
					<li>
						<c:if test="${not empty id }">
 							<a href="logoutPro.do">LOGOUT</a>
 						</c:if>
 						<c:if test="${empty id }">
 							<a href="loginForm.do">LOGIN</a>
 						</c:if>
					</li>								
					<li><a href="joinForm.do">JOIN US</a></li>										
					<li><a href="cartForm.do">CART</a></li>										
					<li><a href="mypageForm.do">MY PAGE</a></li>										
					<li><a href="boardList.do">BOARD</a></li>					
				</ul>
				<!-- Background Section -->
				<div>
					<div class="intro-text">
						<div class="intro-text">
						<h1><a href="main.do">Choongang</a></h1>
						<h4 class="contents">for you</h4>
					</div>
				</div>
				<!-- Search Section -->
				<form action="search.do">
					<div class="search">
					<select class="search-cate" name="searchCate" size="1">
							<option value="product">상품</option>
							<option value="board">게시판</option>
						</select> 
						<input type="text" class="search-content" name="searchContent" placeholder="검색어 입력" required="required">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Contents Section -->
<%-- 	<br><br><br><br>
	
<table border="2">
	<caption><h2>리뷰 상세</h2></caption>
	<tr>
		<td height="50">작성자</td>
		<td>${review.user_id}</td>
	</tr>
	<tr>
		<td height="50">별점</td>
		<td>${review.rev_avg}</td>
	</tr>
	<tr>
		<td height="50">내용</td>
		<td>${review.rev_context}</td>
	</tr>
	<tr>
		<td height="50">작성일</td>
		<td>${review.rev_reg_date}</td>
	</tr>
	
	<tr>
		<td colspan="2" height="50" align="center">
			<c:if test="${rd_idck eq 1}">
				<input type="button" value="수정" 
					onclick="location.href='reviewUpdateForm.do?rev_code=${review.rev_code}&pageNum=${pageNum}&pd_code=${pd_code }'">
			</c:if>
			<c:if test="${rd_idck eq 1}">
				<input type="button" value="삭제" 
					onclick="location.href='reviewDeleteForm.do?rev_code=${review.rev_code}&pageNum=${pageNum}&pd_code=${pd_code }'">
			</c:if>
			<input type="button" value="목록" 
				onclick="location.href='reviewList.do?pageNum=${pageNum}&pd_code=${pd_code }'">
		</td>
	</tr>					 
</table> --%>
	<!-- ==================================================================================== -->
	<br><br><br><br>
	
	<div class="content"> 
		<th>Review Content</th>
	</div>
	
	<table>
		<tr><th>작성자</th><td>&nbsp;&nbsp;&nbsp;&nbsp;${review.user_id}</td></tr>
		<tr><th>별점</th><td>&nbsp;&nbsp;&nbsp;&nbsp;${review.rev_avg}</td></tr>
		<tr><th>내용</th><td>&nbsp;&nbsp;&nbsp;&nbsp;${review.rev_context }</td></tr>
		<tr><th>작성일</th><td>&nbsp;&nbsp;&nbsp;&nbsp;${review.rev_reg_date }</td></tr>
		
	</table>	
	
		<div class="button">
		<c:if test="${rd_idck eq 1}">
			<input type="button" value="수정"
				onclick="location.href='reviewUpdateForm.do?rev_code=${review.rev_code}&pageNum=${pageNum}&pd_code=${pd_code }'">
		</c:if>
		
		<c:if test="${rd_idck eq 1}">
			<input type="button" value="삭제"
					onclick="location.href='reviewDeleteForm.do?rev_code=${review.rev_code}&pageNum=${pageNum}&pd_code=${pd_code }'">
		</c:if>

		<input type="button" value="목록"
					onclick="location.href='reviewList.do?pageNum=${pageNum}&pd_code=${pd_code }'">				 
		</div>
	</div>
	<!-- Footer Section-->
	<div class="footer">
		<div class="footer-text">
			CHOONGANG <br> 
			사업자등록번호 : 202-10-92707 | 대표 : 정중앙 | 서울특별시 마포구 대흥동 12-20 3층 301호 <br>
			통신판매업 신고 : 제 2021-서울마포-00301 호 | 개인정보관리책임자 : 정중앙 <br>			
		</div>
	</div>
</div>	
</body>
</html>