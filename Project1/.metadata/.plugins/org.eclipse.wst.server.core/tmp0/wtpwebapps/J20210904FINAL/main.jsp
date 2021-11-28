<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Choongang Shopping</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/common/slider.css" type="text/css">
<link rel="stylesheet" href="css/common/main.css" type="text/css">
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
		<!-- Slider Section -->
		<div class="slider">
			<input type="radio" name="slide" id="slide1" checked> 
			<input type="radio" name="slide" id="slide2">
			<input type="radio" name="slide" id="slide3">
				<ul id="imgholder" class="imgs">
					<li><a href='pdetailForm.do?pdCode=P0011'><img src="images/main/slide_img1.png"></a></li>										
					<li><a href='pdetailForm.do?pdCode=P0004'><img src="images/main/slide_img2.png"></a></li>										
					<li><a href='pdetailForm.do?pdCode=P0010'><img src="images/main/slide_img3.png"></a></li>				
				</ul>
			<div class="bullets">
				<label for="slide1">&nbsp;</label> <label for="slide2">&nbsp;</label>
				<label for="slide3">&nbsp;</label>
			</div>
		</div>
		<!-- Event Section -->
		<div class="event">
			<h1>EVENT</h1>
			<ul class="icons">
				<li>
					<div class="icon-img">
						<a href="plist.do?cateCode=1">
						<img src="images/main/btn_img1.png"></a>	
					</div>
				</li>
				<li>
					<div class="icon-img">
						<a href="plist.do?cateCode=hot"> 
						<img src="images/main/btn_img2.png"></a>
					</div>
				</li>
				<li>
					<div class="icon-img">
						<a href="reviewWriteForm.do?pageNum=1&pd_code=P0004">
						<img src="images/main/btn_img3.png"></a>
					</div>
				</li>
			</ul>
		</div>
	<!-- Category Section -->
	<div class="category">
		<h3>BEST</h3>
			<div class="cg-list1">
				<a href='pdetailForm.do?pdCode=${best_pd_code["1"]}' ><img class="cg-img" src='${best_pd_img["1"] } '></a>
				<a href='pdetailForm.do?pdCode=${best_pd_code["2"]}' ><img class="cg-img" src='${best_pd_img["2"] } '></a>
				<a href='pdetailForm.do?pdCode=${best_pd_code["3"]}'><img class="cg-img" src='${best_pd_img["3"] } '></a>
			</div>
		<h3>NEW ARRIVAL</h3>
			<div class="cg-list2">
				<a href='pdetailForm.do?pdCode=${new_pd_code["1"]}'><img class="cg-img" src='${new_pd_img["1"] }'></a>
				<a href='pdetailForm.do?pdCode=${new_pd_code["2"]}'><img class="cg-img" src='${new_pd_img["2"] }'></a>
				<a href='pdetailForm.do?pdCode=${new_pd_code["3"]}'><img class="cg-img" src='${new_pd_img["3"] }'></a>
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