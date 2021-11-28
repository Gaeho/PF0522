<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function fn_chk() {
		var list = new Array();
		var listCnt  = 0;
		var price = 0;
		var sum  = 0;
		var shipping_fee = 0;
		var pd_code_list = "";
		var pd_qty_list = "";
		var pd_qty_listAll = "";
		if(${cartList.size() }==1){
			//alert(${cartList.size() });
	        if( document.frm.pd_code.checked == true ){
	        	// 판매가
		        //
		        //alert("pd_code value->"+frm.pd_code[listCnt].value);
		        //alert("pd_qty value->"+frm.pd_qty[listCnt].value);
		        price = parseInt(frm.pd_price.value) * parseInt(frm.pd_qty.value) ;
		        sum += price;
		        pd_code_list += frm.pd_code.value;
		        pd_qty_list += frm.pd_qty.value;
		        //alert(" pd_code_list->"+ pd_code_list);
		        //alert(" pd_qty_list->"+ pd_qty_list);
		        shipping_fee=2500;
        	}
	        pd_qty_listAll += frm.pd_qty.value;
		}else{
			<c:forEach items="${cartList }" var="list" >	
	        if( document.frm.pd_code[listCnt].checked == true ){
	        	// 판매가
		        //
		        //alert("pd_code value->"+frm.pd_code[listCnt].value);
		        //alert("pd_qty value->"+frm.pd_qty[listCnt].value);
		        price = parseInt(frm.pd_price[listCnt].value) * parseInt(frm.pd_qty[listCnt].value) ;
		        sum += price;
		        pd_code_list += frm.pd_code[listCnt].value+" ";
		        pd_qty_list += frm.pd_qty[listCnt].value+" ";
		        //alert(" pd_code_list->"+ pd_code_list);
		        //alert(" pd_qty_list->"+ pd_qty_list);
		        shipping_fee=2500;
        	}
	        pd_qty_listAll += frm.pd_qty[listCnt].value+" ";
	        listCnt = listCnt + 1;
		</c:forEach>
		}
	 	
		
        //alert("sum->"+sum);
        frm.total_sum.value = numberWithCommas(sum+shipping_fee);
    	frm.pd_code_list.value = pd_code_list;
    	frm.pd_qty_list.value = pd_qty_list;
    	frm.pd_qty_listAll.value = pd_qty_listAll;
	}	
	
	function selectAll(selectAll)  {
		  const checkboxes 
		     = document.querySelectorAll('input[type="checkbox"]');
		  
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = selectAll.checked
		  })
		}
	
	function fn_select() {
		//var quantity = frm.pd_code_list.value;
		location.href='orderList.do?pd_code_list='+frm.pd_code_list.value+'&pd_qty_list='+frm.pd_qty_list.value;
	}
	
	function fn_allSelct(){
		action='fn_chk()';
		location.href='orderList.do?pd_qty_listAll='+frm.pd_qty_listAll.value+'&select_check=SC';
	}
	
	// x는 숫자
	function numberWithCommas(x) {
	  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	}
</script>
<link rel="stylesheet" href="css/common/header.css" type="text/css">
<link rel="stylesheet" href="css/common/footer.css" type="text/css">
<link rel="stylesheet" href="css/cart/cartForm.css" type="text/css">
<!-- Web Font Link -->
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
	<div class="cart_list">
			<th>Cart list</th>
			</div>
			
			<c:if test="${cartList.isEmpty() eq true }">
			
		<div class="box">
			<i class="fas fa-shopping-cart fa-4x"></i><br><br>
	   		<th>장바구니가 비어있습니다.</th>	
		</div>
		   
			<div class="d_button">
				<input type="button" value="쇼핑계속하기" onclick="location.href='plist.do'">
			</div>
				
		</c:if>


	<c:if test="${cartList.isEmpty() eq false }">
		<form name="frm">
			<div class="list">
				<table border="1px" align="center">
					<tr>
						<th> <input id="selectAll1" type="checkbox" value="selectAll"
							onclick="selectAll(this)" onchange="fn_chk()">
							<label for="selectAll1">전체선택 </label>
						</th>
						<th>이미지</th>
						<th>상품정보</th>
						<th>판매가</th>
						<th>수량</th>
						<th>배송비</th>
					</tr>

					<c:forEach var="cartList2" items="${cartList}" varStatus="status">
						<tr height="300">

							<td><input class="checkbox22" type="checkbox" name="pd_code"
								value="${cartList2.getPd_code() }" onClick="fn_chk()"> <input
								type="hidden" name="pd_price"
								value="${cartList2.getPd_price() }"> </td> 
							<td><img width="200" src="${cartList2.getImgPath() }"
								name="pdImage"></td>
							<td width="250" align="center">${cartList2.getPd_name() }</td>
							<%-- <td width="200" align="center">${cartList2.getPd_price()}원</td> --%>
							<td width="200" align="center"><fmt:formatNumber value="${cartList2.getPd_price()}" pattern="#,###" /> 원</td>
							<td width="200"><input type="number" name="pd_qty" class="qty_bar" value="${cartList2.getQty() }" min="1" onchange="fn_chk()">								
							<input type="button" class="delete_btn" value="삭제" onclick="location.href='cartDeletePro.do?pd_code=${cartList2.getPd_code() }'"></td>										
							<c:if test="${status.first}">
								<td width="200" align="center" rowspan="${cartList.size() }">2,500원</td>
							</c:if>
						</tr>


					</c:forEach>


				</table>
			</div>

			<div class="tot_price">
			
				<div>
					총 상품 금액:  <input name="total_sum"
							class="tot_price_bar" type="text" size="20" readonly> 원
				</div>
				
			</div>
			<br> <br> <br>
			<table class="click_box">
				<div class="button">

					<!-- <input type="hidden" name="select_check" value="SC"> -->
					<input type="hidden" name="pd_qty_listAll" value="pd_qty_listAll">
					<input type="button" value="전체상품구매" onclick="fn_allSelct()">


					<input type="hidden" name="pd_code_list" value="pd_code_list">
					<input type="hidden" name="pd_qty_list" value="pd_qty_list">
					<input type="button" value="선택상품구매" onClick="fn_select()">

					<input type="button" value="쇼핑계속하기"
						onclick="location.href='plist.do'">
				</div>
			</table>
		</form>
	</c:if>
	
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