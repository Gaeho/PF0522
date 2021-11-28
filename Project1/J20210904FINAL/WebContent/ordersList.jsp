<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 정보</title>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/order/orderList.css" type="text/css">
<!-- Web Font Link-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lora&family=Noto+Sans+KR&family=Raleway:wght@300&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/df2c81d9ba.js" crossorigin="anonymous"></script>
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
   <c:if test="${result==-1 }">
      <script type="text/javascript">
         alert("선택한 상품이 없습니다");
         location.href=history.back();
      </script>
   </c:if>
   
   <c:if test="${result==-2 }">
      <script type="text/javascript">
         alert("잘못된 접근입니다");
         location.href=history.back();
      </script>
   </c:if>
   <!--==================목록페이지==================== -->
   <form action="orderPro.do" method="post">
      
         <div class="orders_info">
            <th>주문자 정보</th>
         </div>

         <table class="order_info222">
         <tr>
            <th>이름</th>
            <td><input type="text" name="user_name" maxlength="20" placeholder="이름을 입력하세요."
                  value="${user.getUser_name() }"></td>
         </tr>
         <br>
         <tr>   
            <th>전화번호</th>
            <td><input type="text" name="user_id" maxlength="20" placeholder="전화번호을 입력하세요."
                  value="${user.getUser_tel() }"></td>
         </tr>
         <br>
         <tr>
            <th>이메일</th>
            <td><input type="text" name="user_email" maxlength="20" placeholder="이메일을 입력하세요."
                  value="${user.getUser_email()}"></td>
         </tr>
         </table>
         

         
         <div class="address_info">
            <th>배송지 정보</th>
         </div>
         
         <table class="address_info_box">
         <tr>
            <th>수령인</th>
            <td><input type="text" name="ord_receiver" maxlength="10" placeholder="수령인을 입력하세요."
                  value="${user.getUser_name() }"></td>
         </tr>
         <br>
         <tr>
            <th>전화번호</th>
            <td><input type="text" name="user_tel" maxlength="15" placeholder="전화번호를 입력하세요."
                  value="${user.getUser_tel() }"></td>
            </tr>
            <br>
            <tr>
               <th>주소</th>
               <td><input type="text" name="user_addr" maxlength="10" placeholder="주소를 입력하세요."
                  value="${user.getUser_addr()}"></td>
            </tr>
            <br>
            <tr>
               <th>배송메모</th>
               <td><input type="text" name="ord_memo" maxlength="10" placeholder="배송메모를 입력하세요."></td>
            </tr>
         </table>
         
         
         <div class="product_info">
            <th>주문 상품</th>
         </div>

         <table class="product_info222">
         
            <tr>
               <th>상품정보</th>
               <th>판매가</th>
               <th>수량</th>
            </tr>
            
               <c:set var="totCost" value="0"></c:set>
               <c:forEach var="product_list" items="${product_list}"
                  varStatus="status">
                  <tr>
                     <td>${product_list.getPd_name() }</td>
                     <td><fmt:formatNumber value="${product_list.getPd_price()}" pattern="#,###" />원</td>
                     <td>${product_list.getQty() }</td></tr>
                     <%-- <td width="200" align="center">${pd_qty_array[status.index] }</td> --%>
                     <c:set var="totCost"
                        value="${totCost + product_list.getPd_price()*product_list.getQty() }"></c:set>

                     <%-- <td width="200" align="center">${totCost }</td> --%>
                  
               </c:forEach>
         </table>
         
         
         <div class="Final_info">
            <th>최종결제 금액</th>
         </div>
         
         <table class="final_info_box">
            <tr>
               <td>총 상품금액</td>
               <td><fmt:formatNumber value="${totCost }" pattern="#,###,###" />원</td>
            </tr>


            <tr>
               <td>배송비</td>
               <td>2,500원</td>
            </tr>


            <tr>
               <td>최종결제 금액 </td>
               <td><fmt:formatNumber value="${totCost+2500}" pattern="#,###,###" />원</td>
            </tr>

            <tr>
               <td>결제 수단</td>
               <td><input type="radio" name="order" value="카드">카드 
               <input type="radio" name="order" value="계좌이체">계좌이체
               <input type="radio" name="order" value="toss">toss 
               <input type="radio" name="order" value="휴대폰">휴대폰</td>
            </tr>
            </table>
            
            <div class="button">
               <td colspan="2">
               <input type="hidden" name="pd_code_list" value="${pd_code_list }">
               <input type="hidden" name="pd_qty_list" value="${pd_qty_list }">
               <input type="submit" value="결제하기"> 
               <input type="button" value="돌아가기" onclick="location.href='cartForm.do'">
               </td>
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