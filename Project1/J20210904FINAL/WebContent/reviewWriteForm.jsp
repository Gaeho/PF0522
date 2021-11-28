<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/review/reviewWrite.css" type="text/css">
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
	<form action="reviewWritePro.do">
	<div class="reviewContents1">
		<input type="hidden" name="user_id" value="${id}">
		<input type="hidden" name="pd_code" value="${pd_code}">
		
			<div class="reivewContents1_child1">
				<th>Review Content</th>
			</div>
		<table>
			<div class="reivewContents1_child2">
				
					<tr>
						<td class="tdddd" height="50">작성자</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;${id}</td>
					</tr>
				
				
					<tr>
					<td class="tdddd" height="50">별점</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;
							<select name="rev_avg">
					  			<option value="1">1</option>
					 	 		<option value="2">2</option>
					  			<option value="3">3</option>
					 	 		<option value="4">4</option>
					 			<option value="5">5</option>
							</select>
						</td>
					</tr>
			
				
					<tr>
						<td class="tdddd" height="50">내용</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;
							<pre class="reviewWWW">
								<textarea id="recontxt" rows="10" cols="40" name="rev_context" maxlength="100"
								required="required" placeholder="내용을 작성해주세요"></textarea>
							</pre>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				
					<tr>
						<td class="tdddd" height="50">작성일</td>
						<td id="td_datetime">&nbsp;&nbsp;&nbsp;&nbsp;
						<script type="text/javascript">
								var date = new Date().toLocaleString();
								document.getElementById("td_datetime").innerText = date.slice(0, 12);
						</script>
						</td>
					</tr>
			
				</table>
			</div>
			<div class="buttonFamily">		
					<!-- 전 페이지 목록으로 -->
					<input type="button" value="목록" onclick="location.href='reviewList.do?pd_code=${pd_code }&pagNum=${pageNum}'">
					<!-- 게시판 추가  -->
					<input type="submit" value="등록">
					<!-- 글 내용 삭제 -->
					<input type="reset" value="다시 작성" >
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