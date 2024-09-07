<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account, model.Goods, java.util.ArrayList" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% 
	// セッションスコープ
	Account loginAccount = (Account)session.getAttribute("loginAccount");
	ArrayList<Goods> goods = (ArrayList<Goods>)session.getAttribute("goods");
	int goods_id = (int)request.getAttribute("goods_id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="././css/style.css">
<title>商品変更 | Sweets Shop</title>
</head>
<body class="updateGoods">
<!-- ヘッダー -->
<header>
<div class="container">
<div class="item"><h1>Sweets shop</h1></div>
<div class="item"><h5/>LoginUser : <%= loginAccount.getAccount_id() %><a href="LogoutServlet">  ログアウト</a></h5></div>
</div>

</header>

	<!-- メイン -->
	<main>

		<div class="list">
			<p>商品情報の変更</p>

			<form action="UpdateGoodsServlet" method="post">
			<% for(Goods g : goods){
				if(g.getGoods_id() == goods_id){%>
				<table>
					<tr>
						<th>商品ID</th>
						<td><%=g.getGoods_id()%><div style="color: red">※商品IDは変更不可</div></td>
						<!-- 要素番号を取得 -->
						<input type="hidden" name="goods_id" value="<%= g.getGoods_id() %>" />
					</tr>
					<tr>
						<th>商品名</th>
						<td><input type="text" name="goods_name"
							value="<%=g.getGoods_name()%>"></td>
					</tr>
					<tr>
						<th>商品説明</th>
						<td><textarea name="goods_text"><%=g.getGoods_text()%></textarea></td>
					</tr>
					<tr>
						<th>金額</th>
						<td><input type="number" name="price" value="<%=g.getPrice()%>" min="1" max="10000"></td>
					</tr>
					<tr>
						<th>在庫数</th>
						<td><input type="number" name="stock" value="<%=g.getStock()%>" style="width:30px" min="0"></td>
					</tr>
			</table>
			<hr>
			<% } } %>
				<p style="color: red">
					<c:out value="${errorMsg}" escapeXml="false" />
				</p>
				<input class="chenge_submit" type="submit" value="変更"><br><br>
			</form>
		</div>
		<a href="AdminServlet">前に戻る</a>
		<a href="MenuServlet">メニューへ</a>


	</main>

</body>
</html>