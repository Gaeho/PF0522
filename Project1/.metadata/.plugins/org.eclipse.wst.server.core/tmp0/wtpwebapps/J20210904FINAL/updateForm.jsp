<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 수정</title>
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
<link rel="stylesheet" href="css/mypage/personalinfo/updateform.css" type="text/css">
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

		<br> <br>

		<!-- 여기까지 3가지 메뉴창  mypagestyle.css 적용-->

		<form action="updatePro.do" method="post">
			<table class="PinfoTable2">
				<tr>
					<td id="ptabletitle2" colspan="2" align="right">개인정보 변경
				</tr>
				<tr>
					<td id="ptableleft2">이름</td>
					<td id="ptableright2">${idUpdateForm.user_name}</td>
				</tr>
				<tr>
					<td id="ptableleft2">아이디</td>
					<td id="ptableright2">${id}<input type="hidden" name="id"
						value="${id}"></td>
				</tr>

				<tr>
					<td id="ptableleft2">암호</td>
					<td id="ptableright2"><input type="password" name="pw"
						value="${idUpdateForm.user_pw}" required="required" maxlength="20"
						placeholder="변경할 암호를 입력하세요"></td>
				</tr>


				<tr>
					<td id="ptableleft2">전화번호</td>
					<td id="ptableright2"><input type="tel" name="tel"
						required="required" pattern="\d{2,3}-\d{3,4|-\d{4}"
						value="${idUpdateForm.user_tel}"></td>
				</tr>
				<tr>
					<td id="ptableleft2">이메일</td>
					<td id="ptableright2"><input type="text" name="email"
						required="required" maxlength="25" value="${idUpdateForm.user_email}"></td>
				</tr>
				<tr>
					<td id="ptableleft2">주소</td>
					<td id="ptableright2"><input type="text" name="addr" maxlength="50"
						required="required" value="${idUpdateForm.user_addr}"
						style="text-align: left; width: 300px;"></td>
				</tr>

				<tr>
					<td colspan="2" id="tablebuttons1"><input id="pbtn2"
						type="submit" value="확인"> <input id="pbtn2" type="reset"
						value="재입력"> <input id="pbtn2" type="button"
						onclick="location='personalInfo.do'" value="돌아가기"> <!-- <button onclick="location='personalInfo.do'">돌아가기</button> -->
					</td>

				</tr>
			</table>


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