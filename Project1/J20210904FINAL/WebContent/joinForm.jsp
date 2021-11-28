<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/join/join.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<!-- Web Font Link -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora&family=Noto+Sans+KR&family=Raleway:wght@300&display=swap" rel="stylesheet">
</head>
<script type="text/javascript">
	//form 검사
	function checkValue() {

		//form submit 비밀번호 중복 체크
/* 		if (!chk()) {
			return false;
		} */
		
		if (join.pw.value != join.pw2.value) {
			alert("비밀번호가 다릅니다. 다시 확인해주세요");
			return false;
		}
		return true;

	}

	//-------------------------------------------------------------------

	//아이디 중복체크
	function IdChk() {

		// form 에 입력된 join.id.value 
		// select where join.id.value => 가져온 db 결과물
		location.href = "idCheckPro.do?id=" + join.id.value;
		// => length == 0 유저id 없음 => 가입가능 => alert("사용가능") => 중복체크변수 true
		// => length != 0 => 가입불가 => alert("중복입니다") => 중복체크변수 false

	}

	//비밀번호 재확인 버튼 
/* 	function chk() {
		if (join.pw.value != join.pw2.value) {
			alert("비밀번호가 다릅니다. 다시 확인해주세요");
			return false;
		} else {
			alert("확인되었습니다");
		}

		return true;
	} */
</script>
<body>
<div id="body-wrapper">
		<div id="body-content">
			<!-- Navigation Section -->
			<div class="intro-background">
				<div class="header">
					<ul class="nav">
						<li><c:if test="${not empty id }">
								<a href="logoutPro.do">LOGOUT</a>
							</c:if> <c:if test="${empty id }">
								<a href="loginForm.do">LOGIN</a>
							</c:if></li>
						<li><a href="joinForm.do">JOIN US</a></li>
						<li><a href="cartForm.do">CART</a></li>
						<li><a href="mypageForm.do">MY PAGE</a></li>
						<li><a href="boardList.do">BOARD</a></li>
					</ul>
					<!-- Background Section -->
					<div>
						<div class="intro-text">
							<div class="intro-text">
								<h1>
									<a href="main.do">Choongang</a>
								</h1>
								<h4 class="contents">for you</h4>
							</div>
						</div>
						<!-- Search Section -->
						<form action="search.do">
							<div class="search">
								<select class="search-cate" name="searchCate" size="1">
									<option value="product">상품</option>
									<option value="board">게시판</option>
								</select> <input type="text" class="search-content" name="searchContent"
									placeholder="검색어 입력" required="required">
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- Contents Section -->
			<form class="joinFormDiv" action="joinPro.do" name="join" onsubmit="return checkValue()" method="post">
				<div class="joinFormWrapper">
					<div class="joinFormRow">
						<div class="joinFormLabel">이름</div>
						<div class="joinFormInputBox">
							<input type="text" name="name" required="required" placeholder="이름을 입력하세요">						
						</div>
					</div>
					<div class="joinFormRow">
						<div class="joinFormLabel">아이디</div>
						<div class="joinFormInputBox">
							<input type="text" name="id" required="required"
								onkeydown="inputIdChk()" placeholder="영문/숫자를 입력하세요"><input class="joinFormInputSub" type="button" value="중복체크" onclick="IdChk()">																
						</div>
					</div>
					<div class="joinFormRow">
						<div class="joinFormLabel">비밀번호</div>
						<div class="joinFormInputBox">
							<input type="password" name="pw" required="required" placeholder="비밀번호를 입력해세요" title="영문과 숫자로만 20자 이내로 작성">								
						</div>
					</div>
					<div class="joinFormRow">
						<div class="joinFormLabel">비밀번호 재확인</div>
						<div class="joinFormInputBox">
							<input type="password" name="pw2" required="required" placeholder="정확하게 입력해주세요">						
						</div>
					</div>
					<div class="joinFormRow">
						<div class="joinFormLabel">전화번호</div>
						<div class="joinFormInputBox">
							<input type="tel" name="tel" required="required" pattern="\d{2,3}-\d{3,4}-\d{4}" placeholder="xxx-xxxx-xxxx" title="2,3자리-3,4자리-4자리">												
						</div>
					</div>
					<div class="joinFormRow">
						<div class="joinFormLabel">이메일</div>
						<div class="joinFormInputBox">
							<input type="email" name="email" placeholder="이메일을 입력하세요">
						</div>
					</div>
					<div class="joinFormRow">
						<div class="joinFormLabel">주소</div>
						<div class="joinFormInputBox">
							<input type="text" name="addr" required="required" placeholder="직접입력">							
						</div>
					</div>
				</div>
				<div class="ck">
					<tr>
						<td><input type="submit" value="확인"></td>						
						<td><input type="reset" value="다시작성하기"></td>	
					</tr>
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