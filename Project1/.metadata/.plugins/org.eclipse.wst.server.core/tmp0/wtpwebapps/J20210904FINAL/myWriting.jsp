<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성글 확인</title>

<style type="text/css">
.span1 {
	position: absolute;
	color: black;
	font-size: 30px;
	left: 50%;
	transform: translate(-670px, -20px);
}
</style>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/mypage/mypagestyle.css" type="text/css">
<link rel="stylesheet" href="css/mypage/mycontents/mywriting.css" type="text/css">
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
	<div class="contents">

		<div class="mypage">
			<div class="user_info">
				<p>
					어서오세요 <br>${id}님</p>
			</div>
			<div class="page_menu">
				<div class="menu">
					<a href="orderConfirm.do">주문확인 </a>
				</div>
				<span class="bar1"></span>
				<div class="menu">
					<a href="myWriting.do">작성한글</a>
				</div>
				<span class="bar1"></span>
				<div class="menu">
					<a href="personalInfo.do">개인정보</a>
				</div>
			</div>
		</div>

		<br> <br> <br>

		<!-- 여기까지 3가지 메뉴창  mypagestyle.css 적용-->


		<table class="myWrList01">

			<tr class="mwlistRow2">
				<th id="mwlistnum1">번호</th>
				<th id="mwlisttitle1">제목</th>
				<th id="mwlistdate1">작성일</th>
				<th id="mwlistwriter1">작성자</th>
			</tr>

			<c:if test="${totCnt > 0 }">
				<c:forEach var="board" items="${list}">

					<tr class="mwlistRow1">
						<td align="center">${startNum}</td>

						<td class="mwleft" width=200><c:if
								test="${board.ref_level > 0}">
								<img src='images/board/level.gif' width="${board.ref_level*10}">
								<%--       	board.re_level  ${board.re_level} --%>
								<img src='images/board/re2.jpg'>
							</c:if> <a
							href='myContent.do?code=${board.brd_code}&pageNum=${currentPage}'>
								${board.brd_subject}</a></td>

						<td align="center">${board.brd_reg_date}</td>

						<td align="center">${id}</td>

					</tr>
					<c:set var="startNum" value="${startNum - 1 }" />
				</c:forEach>
			</c:if>


			<c:if test="${totCnt == 0 }">
				<tr>
					<td colspan=7>아직 회원님의 문의글이 없습니다.</td>
				</tr>
			</c:if>

		</table>

		<div style="text-align: center;">
			<c:if test="${startPage > blockSize }">
				<a href='myWriting.do?pageNum=${startPage-blockSize}'>[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href='myWriting.do?pageNum=${i}'>[${i}]</a>
			</c:forEach>
			<c:if test="${endPage < pageCnt }">
				<a href='myWriting.do?pageNum=${startPage+blockSize}'>[다음]</a>
			</c:if>
		</div>
	</div>
	</div>
	<!-- Footer Section -->
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