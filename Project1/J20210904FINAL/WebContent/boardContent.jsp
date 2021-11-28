<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="css/board/boardcontentstyle.css" type="text/css">
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
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
	<div class="contents">
		<div class="bdContent1">
			<div id="bdheadpoint1">
				<div id="bdfont1">게시판 상세내역</div>
			</div>
			<div class="bdcontentsboard1">
				<div class="bdupperCont1">

					<div>
						<h1 id="bdtitle1">${board.brd_subject}</h1>
					</div>
					<%-- <div id="bdcontrow1">문의상품 : ${pd_code}</div> --%>
					<c:if test="${not empty pd_name }">
						<div id="bdcontrow1">문의상품 : ${pd_name}</div>
					</c:if>
					<div id="bdcontrow1">작성자 : ${board.user_id}</div>
					<div id="bdcontrow1_inline_left">게시글 번호 : ${brd_code }</div>
					<div id="bdcontrow1_inline_right">${board.brd_reg_date}</div>
				</div>

				<div class="bdlowerCont1">
					<div id="bdcontext1">${board.brd_context}</div>
				</div>
			</div>
			<div class="bdrightform1">
				<c:if test="${id eq board.user_id or adck eq 1}">
					<input type="button" id="bdbtn1" value="수정"
						onclick="location.href='boardUpdateForm.do?brd_code=${board.brd_code}&pageNum=${pageNum }'">
				</c:if>


				<c:if test="${bd_idck eq 1 }">
					<input type="button" id="bdbtn1" value="답변작성"
						onclick="location.href='boardWriteForm.do?brd_code=${board.brd_code}&pageNum=${pageNum }'">
				</c:if>

				<c:if test="${id eq board.user_id or adck eq 1}">
					<input type="button" id="bdbtn1" value="삭제"
						onclick="location.href='boardDeleteForm.do?brd_code=${board.brd_code}&pageNum=${pageNum }'">
				</c:if>

				<input type="button" id="bdbtn1" value="목록"
					onclick="location.href='boardList.do?pageNum=${pageNum }&searchContent=${searchContent}'">

			</div>
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