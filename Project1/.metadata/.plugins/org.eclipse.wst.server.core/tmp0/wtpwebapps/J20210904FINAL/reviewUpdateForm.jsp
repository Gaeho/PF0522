<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 수정</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/review/reviewUpdate.css" type="text/css">
<!-- Font Link-->
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
<form action="reviewUpdatePro.do" method="post">
	<div class="reviewUContents">
	<input type="hidden" name="user_id" value="${review.user_id }">
	<input type="hidden" name="pageNume" value="${pageNum }">
	<input type="hidden" name="rev_code" value="${review.rev_code}">
	<input type="hidden" name="rev_avg" value="${review.rev_avg}">
	<input type="hidden" name="pd_code" value="${pd_code}">
	
	<div class="reivewUContents1_child1">
				<th>Review Update</th>
	</div>
	
	<div class="reivewUContents1_child2">
	<c:if test="${rd_idck eq 1 }">
		<table>
			
			<div class="reviewUW">
			<tr>
				<td class="tdddd222" height="50">작성자</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;${review.user_id}</td>
			</tr>
			</div>
			<tr>
				<td class="tdddd222" height="50">별점</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;${review.rev_avg}</td>
			</tr>
			<tr>
				<td class="tdddd222" height="50">내용</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;
					<pre class="reviewUw">
						<textarea id="revctxt" rows="10" cols="40" maxlength="100" name="rev_context" required="required">${review.rev_context}</textarea>
					</pre>&nbsp;&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
			<tr>
				<td class="tdddd222" height="50">작성일</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;${review.rev_reg_date}</td>
			</tr>		
	</table>
			<div class="reUbu">
				<input type="submit" value="확인">
				<input type="reset"	 value="다시작성">
			</div>
	</c:if>
	</div>
	<div align="center">
		<c:if test="${rd_idck eq 0 }">
			<h3>권한이 없습니다</h3>
			<button type="button" onclick="javascript:history.back();">이전페이지로</button>
		</c:if>	
	</div>
	</div>
	</form>
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