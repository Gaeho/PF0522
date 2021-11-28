<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/product/pList.css" type="text/css">
<!-- Web Font Link -->
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
	<!-- Main List Section -->
		<ul class="mainlist">			
			<li><div class="ml-text" onclick="location.href='plist.do?cateCode=hot'">BEST</div></li>						
			<li><div class="ml-text" onclick="location.href='plist.do?cateCode=1'">TOP</div></li>						
			<li><div class="ml-text" onclick="location.href='plist.do?cateCode=2'">BOTTOM</div></li>						
			<li><div class="ml-text" onclick="location.href='plist.do?cateCode=3'">SHOES</div></li>			
		</ul>
	<!-- Contents Section -->	
	<!-- 현재시간 toDay에 저장 -->
	<jsp:useBean id="toDay" class="java.util.Date" />	
		<div class="container12">
					<nav class="pCategory">
						<ul class="catelist1">
							<li class="catelist2"><a href="plist.do?sort=pd_Count desc&pageNum=${currentPage}&cateCode=${cateCode}&searchContent=${searchContent}">인기순
									|</a></li>
							<li class="catelist2"><a href="plist.do?sort=pd_Reg_Date&pageNum=${currentPage}&cateCode=${cateCode}&searchContent=${searchContent}">최신순
									|</a></li>
							<li class="catelist2"><a href="plist.do?sort=pd_Price&pageNum=${currentPage}&cateCode=${cateCode}&searchContent=${searchContent}">낮은가격
									|</a></li>
							<li class="catelist2"><a
								href="plist.do?sort=pd_Price desc&pageNum=${currentPage}&cateCode=${cateCode}&searchContent=${searchContent}">높은가격
									</a></li>
						</ul>
					</nav>
				<c:if test="${totCnt > 0 }">
					<div class="list">
						<c:forEach var="item" items="${plist}">
							<ul>
								<li>
									<ul class="list_child" style="font-size: 15px">
										<li>
											<a href="pdetailForm.do?pdCode=${item.pd_code}&pageNum=${currentPage}">
											<img class="listphoto" src="${item.imgPath}" style="margin: 20px 20px 0px 0px"></a>
										</li>
											<ul class="icon">
													<fmt:parseNumber value="${toDay.time / (1000*60*60*24)}" integerOnly="true" var="toDay_N" scope="request" />
													<fmt:parseNumber value="${item.pd_reg_date.time / (1000*60*60*24)}" integerOnly="true" var="pdRegDate_N" scope="request" />
												<c:if test="${(toDay_N - pdRegDate_N) < 23}">
													<li><img src="images/icon/new_icon.png" width="30px" height="30px"></li>&nbsp;
												</c:if>
												<c:if test="${item.pd_count >= 30}">
													<li><img src="images/icon/best_icon.png" width="30px"	height="30px"></li>
												</c:if>
											</ul>
										<li>
											<a href="pdetailForm.do?pdCode=${item.pd_code}&pageNum=${currentPage}">${item.pd_name}</a>
										</li>
										<li>
											<a href="pdetailForm.do?pdCode=${item.pd_code}&pageNum=${currentPage}"><fmt:formatNumber value="${item.pd_price}" pattern="#,###" /></a>
										</li>
									</ul>
								</li>
							</ul>
						</c:forEach>
					</div>
				</c:if>
				<c:if test="${totCnt == 0 }">
					<div class="non-text"><h2>상품이 존재하지 않습니다.</h2></div>
				</c:if>
			
				<div style="text-align: center">
					<c:if test='${startPage > blockSize }'>
						<a href='plist.do?sort=${ sort}&pageNum=${startPage-blockSize}&cateCode=${cateCode}&searchContent=${searchContent}'>[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href='plist.do?sort=${ sort}&pageNum=${i}&cateCode=${cateCode}&searchContent=${searchContent}'>[${i}]</a>
					</c:forEach>
					<c:if test="${endPage < pageCnt }">
						<a href='plist.do?sort=${ sort}&pageNum=${startPage+blockSize}&cateCode=${cateCode}&searchContent=${searchContent}'>[다음]</a>
					</c:if>
				</div>

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