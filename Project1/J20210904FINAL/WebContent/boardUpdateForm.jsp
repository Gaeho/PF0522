<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 업데이트</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/board/boardupdate.css" type="text/css">
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
		<form action="boardUpdatePro.do" method="post">
			<input type="hidden" name="brd_code" value="${board.brd_code }">
			<input type="hidden" name="user_id" value="${board.user_id }">
			<input type="hidden" name="pageNum" value="${pageNum }">
			<c:if test="${bd_idck eq 1 }">
				<div class="myContent2">
					<div id="headpoint2">
						<div id="font22" align="right">No.${board.brd_code } 게시글 수정</div>
					</div>
					<div class="contentsboard2">
						<div class="upperCont2">
							<!-- 제목  -->
							<div class="bdtitle1">
								<input class="titleinfo1" type="text" name="brd_subject" maxlength="50"
									required="required" value="${board.brd_subject }"> <br>
							</div>
							<!-- 주문상품리스트  -->
							<%-- <div class="oplist1">
								<label for="orderCode">주문상품선택</label> <select name="pd_code"
									class="oplistinfo1">
									<c:forEach var="list" items="${myOrderList}">
										<option value="${list.getPd_code()}">${list.getPd_name()}</option>
									</c:forEach>
								</select>
							</div> --%>
							<c:if test="${not empty pd_name }">
								<input type="hidden" name="pd_code" value="${pd_code }">문의상품 : ${pd_name }<br>
							</c:if>
						</div>
						<div class="lowerCont2">
							<textarea id="context2" name="brd_context" maxlength="300" required="required">${board.brd_context }</textarea>
						</div>
						<div class="btnoutside1" align="right">
							<input type="submit" id="btn77" value="수정완료">
							<!-- 전 페이지 목록으로 -->
							<input id="btn77" type="button" value="목록"
								onclick="location.href='boardList.do?pagNum=${pageNum}'">
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${bd_idck eq 0 }">
				<h3>권한이 없습니다</h3>
				<button type="button" onclick="javascript:history.back();">이전페이지로</button>
			</c:if>
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