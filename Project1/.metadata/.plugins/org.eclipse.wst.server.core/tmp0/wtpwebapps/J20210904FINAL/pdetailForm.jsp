<%@page import="dao.ProductDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
<script type="text/javascript">
function gocart()
{
	var quantity = document.getElementById('quantity').value
	location.href="cartinsert.do?quantity="+quantity+"&pd_code=${product.pd_code}";
}
</script>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/product/pdetail.css" type="text/css">
<!-- Web Font Link -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora&family=Noto+Sans+KR&family=Raleway:wght@300&display=swap" rel="stylesheet">
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
	<div class="wrap">
	<h3>Product Info</h3>
		<form action="cartInsertPro.do" method="post"></form>
			<div class="product-view">
				<h2>${product.getPd_name()}</h2>
				<table>
					<colgroup>
						<col style="width: px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th>가 격</th>
							<td><fmt:formatNumber value="${product.getPd_price()}" pattern="#,###" /> 원</td>
						</tr>
						<tr>
							<th>품 번</th>
							<td>${product.getPd_code()}</td>
						</tr>
						<tr>
							<th>분 류</th>
							<td class="pd-class">${product.getPd_category()}</td>
						</tr>
						<tr>
							<th>수 량</th>
							<td>
								<div class="length">
									<input type="number" id="quantity" name="quantity" min="1" value="1">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="pd-img">
					<img src="${imglist[0].getImg_path()}" alt="">
					<ul>
						<li class="on">
						<img src="${imglist[0].getImg_path()}" alt=""></li>
					</ul>
				</div>
				<div class="btns">
					<a class="btn1" onclick=gocart()>장바구니</a> 
					<a class="btn2" onclick="location.href ='reviewList.do?pd_code=${product.pd_code}'">리뷰</a>
					<a class="btn3" onclick="history.back()">이전</a>
				</div>
		</div>
	</div>
	<div class="scroll-img">
		<img src="${imglist[1].getImg_path()}">
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