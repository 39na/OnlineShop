<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Goods, java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	//セッションスコープ
	ArrayList<Goods> goods = (ArrayList<Goods>)session.getAttribute("goods");
	
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<!--  <meta name="viewport" content="width=device-width, initial-scale=1"> -->
	<title>商品一覧 | Sweets Shop</title>
	<link rel="stylesheet" href="././css/style.css">
</head>

<body class="top">
	<div class="container">
		<div class="item">
			<h1 class="logo">Sweets Shop</h1>
		</div>
		<div class="item">
			<a href="LogoutServlet" class="button">ログアウト</a>
		</div>
	</div>
	<!-- カートの中身を表示 -->
	<a href="CartContentsServlet" class="button">カートを見る</a>
	<!-- メニューへ戻る -->
	<a href="MenuServlet" class="button">メニューへ</a><br>
	<hr>

	<h1 class="hero">Product List</h1>

	<div class="item_bgcolor">
	
	<!-- 商品情報 -->
	<div class="itemtable">
		<% for(Goods g : goods){ %>
		<form action="CartInServlet" method="post">
		<div class="item_border">
			<!-- 画像 -->
			<img src="images/<%= g.getGoods_id() %>.jpg" alt="<%= g.getGoods_name() %>"><br>
			<!-- 商品名 -->
			<p class="font_strong"><%= g.getGoods_name() %></p>
			<!-- 商品説明 -->
			<p class="font_small"><%= g.getGoods_text() %></p>
			<!-- 金額・残りの数 -->
			<p class="font_strong">\<%= g.getPrice() %>- /
				<%if(g.getStock() == 0) { %>
					sold out
				<%} else { %>
					<%= g.getStock() %> more
				<%} %></p>
			<!-- 注文数・カートへ入れる -->
			<p>How many.. <input type="number" name="order_quantity" value="0" style="width:30px" min="0" max="<%= g.getStock() %>" class="order_num">
				<%if(g.getStock() == 0) { %>
					<input type="submit" value="To Cart" disabled="disabled" class="button">
				<%} else { %>
					<input type="submit" value="To Cart" class="button">
				<%} %></p>
			<!-- 注文情報を取得 -->
			<input type="hidden" name="goods_id" value="<%= g.getGoods_id() %>" />
		</div>
		</form>
		<% } %>
	</div>
	</div>
	
	
	
	
	<hr>
	<c:if test="${errorMsg.length() != 0}">
		<p style="color:red"><c:out value="${errorMsg}" escapeXml="false"/></p>
	</c:if>
</body>
</html>


