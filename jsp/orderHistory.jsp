<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account,model.OrderInfo, java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	//セッションスコープ
	ArrayList<OrderInfo> orderhistory = (ArrayList<OrderInfo>)session.getAttribute("orderhistory");
   //セッションスコープ
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	String[] items = {"注文ID", "注文日", "商品名", "数量", "小計"};
	int sumAllPrice = 0; // 合計金額
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文履歴 | Sweets Shop</title>
<link rel="stylesheet" href="././css/style.css">
</head>
<body class="orderHistory">
<!-- ヘッダー -->
<header>

<div class="container">
<div class="item"><h1>Sweets shop</h1></div>
<div class="item"><h5/>LoginUser : <%= loginAccount.getAccount_id() %><a href="LogoutServlet">  ログアウト</a></h5></div>
</div>

</header>
<!-- メイン -->
<main>
<div class="orderList">
<p>注文履歴</p>

<c:choose>
	<c:when test="${not empty orderhistory}">
		<table>
			<tr>
				<% for(String item :items){ %>
				<th><%= item %></th>
				<% } %>
			</tr>
			<% for(OrderInfo oh : orderhistory){ 
				sumAllPrice += oh.getTotal_price(); %>
			<tr>
				<td class="tb_1"><%= oh.getOrder_id() %></td>
				<td class="tb_2"><%= oh.getOrder_day() %></td>
				<td class="tb_3"><%= oh.getGoods_name() %></td>
				<td class="tb_4"><%= oh.getOrder_quantity() %></td>
				<td class="tb_5"><%= oh.getTotal_price() %>円</td>
			</tr>
			<% } %>
			<!-- 
			<tr align="right">
				<th colspan="5">合計：<%= sumAllPrice %>円</th>
			</tr>
			 -->
		</table>
		<br>
	</c:when>
	<c:otherwise>
		<p style="color:red">購入履歴がありません</p>
	</c:otherwise>
</c:choose>

</div>

<br>
<a href="MenuServlet">メニューへ</a>

</main>
</body>
</html>