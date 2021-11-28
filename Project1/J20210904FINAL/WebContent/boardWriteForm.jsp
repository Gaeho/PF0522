<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/board/boardwrite.css" type="text/css">
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
	<br>
	<div class="contents">

		<form action="boardWritePro.do">

			<input type="hidden" name="brd_code" value="${brd_code }"> <input
				type="hidden" name="ref" value="${ref }"> <input
				type="hidden" name="ref_level" value="${ref_level }"> <input
				type="hidden" name="ref_step" value="${ref_step }">

			<div class="myContent2">
				<%-- <input type="hidden" name="brd_code" value="${brd_code}"> --%>
				<div id="headpoint2">
					<div id="font22" align="right">문의 작성</div>
				</div>


				<div class="contentsboard2">
					<div class="upperCont2">
						<!-- 제목  -->
						<!-- <label for="subject">제 목</label> -->
						<div class="bdtitle1">
							<input class="titleinfo1" type="text" name="brd_subject"
								placeholder="제목을 입력하세요" maxlength="50"><br>
						</div>
						<!-- 주문상품리스트  -->
						<%-- <div class="oplist1">
							<label for="orderCode">주문상품선택</label> <select name="pd_code"
								class="oplistinfo1">
								<c:forEach var="list" items="${myOrderList}">
									<option value="${list.getPd_code()}">${list.getPd_name()}</option>
								</c:forEach>
							</select>
						</div>
						<br> --%>
						
						<!-- 댓글이 아닐때 -->
						<c:if test="${brd_code == 0 }">
							<div class="oplist1">
								<label for="orderCode">주문상품선택</label>
								<select name="pd_code" class="oplistinfo1">
									<option value="">선택안함</option>
									<c:forEach var="list" items="${myOrderList}">
										<option value="${list.getPd_code()}">${list.getPd_name()}</option>
									</c:forEach>
								</select>
							</div>
							<br>
						</c:if>

						<!-- 댓글일때 -->
						<c:if test="${brd_code > 0 }">
							<c:if test="${not empty pd_name }">
								<input type="hidden" name="pd_code" value="${pd_code }">문의상품 : ${pd_name }<br>
							</c:if>
						</c:if>

						<!-- 전화번호 -->
						<!-- <label for="phoneNumber">전화번호</label>
								<input type="text" name="userTel"><br> -->
					</div>
					<div class="lowerCont2">
						<textarea id="context2" name="brd_context" maxlength="300"></textarea>
					</div>
					<div class="btnoutside1" align="right">

						<!-- 전 페이지 목록으로 -->
						<input id="btn2" type="button" value="목록"
							onclick="location.href='boardList.do?pagNum=${pageNum}'">
						<!-- 게시판 추가  -->
						<input id="btn2" type="submit" value="등록">
						<!-- 글 내용 삭제 -->
						<input id="btn2" type="reset" value="취소">
					</div>
		</form>

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